package com.company.project.secruity;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.company.project.dao.UserAuthorityMapper;
import com.company.project.model.UserAuthority;

@Service
public class JwtUserDetailsServiceImpl  implements UserDetailsService {
	@Resource
    private UserAuthorityMapper userAuthorityMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserAuthority userAuthority =  userAuthorityMapper.getByUserName(userName);
        if (userAuthority.getUser() == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", userName));
        } else {
            return JwtUserFactory.create(userAuthority);
        }
    }
}
