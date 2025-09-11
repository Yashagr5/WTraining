package com.company.filter;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    
    @Value("${app.jwt.secret:playStoreSecretKeyForJWTTokenGeneration2023}")
    private String jwtSecret;
    
    // JWT authentication disabled for development/testing
    @Value("${app.jwt.enabled:false}")
    private boolean jwtEnabled;

    public JwtAuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // Bypass JWT authentication for development/testing
            if (!jwtEnabled) {
                log.debug("JWT authentication is disabled - bypassing security check");
                return chain.filter(exchange);
            }
            
            ServerHttpRequest request = exchange.getRequest();
            
            // Allow OPTIONS requests
            if (request.getMethod() == HttpMethod.OPTIONS) {
                return chain.filter(exchange);
            }
            
            // Skip JWT validation for public endpoints
            String path = request.getURI().getPath();
            if (isPublicPath(path)) {
                log.debug("Public path accessed: {}", path);
                return chain.filter(exchange);
            }

            String token = extractToken(request);
            
            if (!StringUtils.hasText(token)) {
                log.warn("Missing JWT token for path: {}", path);
                return handleUnauthorized(exchange, "Missing JWT token");
            }

            try {
                if (validateToken(token)) {
                    String username = getUsernameFromToken(token);
                    log.debug("Valid JWT token for user: {}", username);
                    
                    // Add username to request headers for downstream services
                    ServerHttpRequest modifiedRequest = request.mutate()
                            .header("X-User-Name", username)
                            .build();
                    
                    return chain.filter(exchange.mutate().request(modifiedRequest).build());
                } else {
                    log.warn("Invalid JWT token for path: {}", path);
                    return handleUnauthorized(exchange, "Invalid JWT token");
                }
            } catch (Exception e) {
                log.error("JWT validation error for path {}: ", path, e);
                return handleUnauthorized(exchange, "JWT validation failed: " + e.getMessage());
            }
        };
    }

    private boolean isPublicPath(String path) {
        // Authentication endpoints - must be public
        if (path.contains("/api/users/register") || 
            path.contains("/api/users/login") ||
            path.contains("/api/owners/register") ||
            path.contains("/api/owners/login")) {
            return true;
        }
        
        // Inter-service communication endpoints
        if (path.contains("/api/owner-notify") ||
            path.contains("/api/users/exists") ||
            path.contains("/api/owners/exists")) {
            return true;
        }
        
        // Public browsing endpoints - users should be able to browse without login
        if (path.contains("/api/categories") ||  // Browse categories
            path.contains("/api/apps/search") ||  // Search apps publicly
            path.contains("/api/apps/featured") || // View featured apps
            path.contains("/api/apps/trending") || // View trending apps
            path.matches("/api/apps/\\d+") || // View individual app details (GET only)
            path.contains("/api/comments/app/") || // View app reviews publicly
            path.contains("/api/downloads/app/") && path.contains("/count")) { // View download count publicly
            return true;
        }
        
        // Health and monitoring endpoints
        if (path.contains("/health") ||
            path.contains("/actuator") ||
            path.contains("/eureka")) {
            return true;
        }
        
        // Development and documentation endpoints
        if (path.contains("/h2-console") ||
            path.contains("/swagger-ui") ||
            path.contains("/v3/api-docs") ||
            path.contains("/webjars")) {
            return true;
        }
        
        // Legacy HTML endpoints (for form-based access)
        if (path.contains("/register") || 
            path.contains("/login") ||
            path.contains("/owners/register-page") ||
            path.contains("/owners/login-page") ||
            path.contains("/users/register-page") ||
            path.contains("/users/login-page")) {
            return true;
        }
        
        // Static resources
        if (path.equals("/") ||
            path.contains("/favicon.ico") ||
            path.contains("/css/") ||
            path.contains("/js/") ||
            path.contains("/images/")) {
            return true;
        }
        
        // Default fallback endpoints
        if (path.contains("FallBack") || path.contains("fallback")) {
            return true;
        }
        
        return false;
    }

    private String extractToken(ServerHttpRequest request) {
        String bearerToken = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private boolean validateToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
            Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.error("Token validation failed: ", e);
            return false;
        }
    }

    private String getUsernameFromToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    private Mono<Void> handleUnauthorized(org.springframework.web.server.ServerWebExchange exchange, String message) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json");
        
        String body = "{\"error\": \"Unauthorized\", \"message\": \"" + message + "\"}";
        org.springframework.core.io.buffer.DataBuffer buffer = response.bufferFactory().wrap(body.getBytes());
        
        return response.writeWith(Mono.just(buffer));
    }

    public static class Config {
        // Configuration properties if needed
    }
}
