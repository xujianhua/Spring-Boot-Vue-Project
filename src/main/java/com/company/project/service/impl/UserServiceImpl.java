package com.company.project.service.impl;

import com.company.project.dao.UserAuthorityMapper;
import com.company.project.dao.UserMapper;
import com.company.project.model.User;
import com.company.project.secruity.JwtTokenUtil;
import com.company.project.secruity.JwtUser;
import com.company.project.service.UserService;
import com.company.project.core.AbstractService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/10/26.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
    private UserDetailsService userDetailsService;
	
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Resource
    private UserAuthorityMapper userAuthorityMapper;
    @Resource
    private UserMapper userMapper;
    
    @Value("${jwt.tokenHead}")
    private String tokenHead;

 

    @Override
    public User register(User userToAdd) {
        final String username = userToAdd.getUsername();
        User filterUser = new User();
        filterUser.setUsername(username);
        if(userMapper.selectOne(filterUser)!=null) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword(encoder.encode(rawPassword));
        userToAdd.setRegisterDate(new Date());
        userToAdd.setLastPasswordResetDate(new Date());
        //userToAdd.setRoles(asList("ROLE_USER"));
        // todo : 添加 角色表 ，添加 用户角色关系表
        userMapper.insert(userToAdd);
        
       
        
        return userToAdd;
    	
    }

    @Override
    public String login(String username, String password) {
    	
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())){
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }
}
