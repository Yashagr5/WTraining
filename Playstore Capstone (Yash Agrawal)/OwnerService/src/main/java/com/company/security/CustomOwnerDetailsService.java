package com.company.security;

import com.company.entity.Owner;
import com.company.repo.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomOwnerDetailsService implements UserDetailsService {
    
    private final OwnerRepository ownerRepository;
    
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Owner owner = ownerRepository.findByOwnerNameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Owner not found with username or email: " + usernameOrEmail));
        
        return new org.springframework.security.core.userdetails.User(
                owner.getOwnerName(),
                owner.getPassword(),
                owner.getIsActive(),
                true,
                true,
                owner.getApprovalStatus() == Owner.ApprovalStatus.APPROVED,
                getAuthorities(owner)
        );
    }
    
    private Collection<? extends GrantedAuthority> getAuthorities(Owner owner) {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_OWNER"));
    }
}
