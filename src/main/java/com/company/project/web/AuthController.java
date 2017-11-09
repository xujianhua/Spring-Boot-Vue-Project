package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.User;
import com.company.project.model.UserAuthority;
import com.company.project.secruity.JwtAuthenticationRequest;
import com.company.project.service.UserAuthorityService;
import com.company.project.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.List;

/**
* Created by CodeGenerator on 2017/10/26.
*/
@RestController
@RequestMapping("/auth")
@Api(value = "AuthController" ,description="用户授权API")
public class AuthController {
	
	@Value("${jwt.header}")
    private String tokenHeader;

	@Resource
    private UserService authService;

    @ApiOperation(value="获取token", notes="用户登录 获取token")
    @RequestMapping(method = RequestMethod.POST,value="/login")
    public Result createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest){
        final String token = authService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        return ResultGenerator.genSuccessResult(token);

    }
    
    @ApiOperation(value="刷新token", notes="刷新token 获取最新token")
    @RequestMapping(method = RequestMethod.POST,value="/refresh")
    public Result refreshAndGetAuthenticationToken(
            HttpServletRequest request) throws AuthenticationException{
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if(refreshedToken == null) {
        	return ResultGenerator.genFailResult("refreshedToken is null");
        } else {
        	return ResultGenerator.genSuccessResult(refreshedToken);
        }
    }
    
    @ApiOperation(value="注册用户", notes="注册返回用户信息")
    @RequestMapping(method = RequestMethod.POST,value="/register")
    public User register(@RequestBody User addedUser) throws AuthenticationException{
        return authService.register(addedUser);
    }

}
