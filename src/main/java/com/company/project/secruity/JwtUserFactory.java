package com.company.project.secruity;
import java.util.List;
import java.util.stream.Collectors;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.company.project.model.Authority;
import com.company.project.model.UserAuthority;

public class JwtUserFactory {
	private JwtUserFactory() {
    }

    public static JwtUser create(UserAuthority ua) {
        return new JwtUser(
        		ua.getUser().getId(),
        		ua.getUser().getUsername(),
        		ua.getUser().getPassword(),
                mapToGrantedAuthorities(ua.getAuthorityList()),
                ua.getUser().getLastPasswordResetDate()
        );
    }
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
    }
}
