package com.company;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/userFallBack")
    public Mono<String> userServiceFallBack() {
        return Mono.just("User Service is taking too long to respond or is down. Please try again later");
    }

    @RequestMapping("/ownerFallBack")
    public Mono<String> ownerServiceFallBack() {
        return Mono.just("Owner Service is taking too long to respond or is down. Please try again later");
    }

    @RequestMapping("/appFallBack")
    public Mono<String> appServiceFallBack() {
        return Mono.just("App Service is taking too long to respond or is down. Please try again later");
    }

    @RequestMapping("/categoryFallBack")
    public Mono<String> categoryServiceFallBack() {
        return Mono.just("Category Service is taking too long to respond or is down. Please try again later");
    }

    @RequestMapping("/commentFallBack")
    public Mono<String> commentServiceFallBack() {
        return Mono.just("Comment Service is taking too long to respond or is down. Please try again later");
    }

    @RequestMapping("/downloadFallBack")
    public Mono<String> downloadServiceFallBack() {
        return Mono.just("Download Service is taking too long to respond or is down. Please try again later");
    }

    @RequestMapping("/notificationFallBack")
    public Mono<String> notificationServiceFallBack() {
        return Mono.just("Notification Service is taking too long to respond or is down. Please try again later");
    }

    @RequestMapping("/defaultFallback")
    public Mono<String> defaultFallback() {
        return Mono.just("Service is temporarily unavailable. Please try again later");
    }
}

//if threshold will leead to 50% call from order to payment will get fail then the status will change from closed (all services are up )to open
//