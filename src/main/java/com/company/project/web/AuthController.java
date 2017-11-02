package com.company.project.web;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.User;
import com.company.project.model.UserAuthority;
import com.company.project.service.UserAuthorityService;
import com.company.project.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;

/**
* Created by CodeGenerator on 2017/10/26.
*/
@RestController
@RequestMapping("/auth")
@Api(value = "AuthController" ,description="用户授权API")
public class AuthController {
    @Resource
    private UserService userService;
    
    @Resource
    private UserAuthorityService userAuthorityService;
   
    
    
    @ApiOperation(value="登录", notes="")
    @RequestMapping(method = RequestMethod.POST,value="/login")
    public Result login(@RequestParam(required=true) String name,@RequestParam(required=true) String password) {
    	User filter =new User();
    	filter.setUsername(name);
    	filter.setPassword(password);
        User loginUser =  userService.Login(filter);
        if(loginUser != null){
        	//登录成功了。
        	
        	
        	return ResultGenerator.genSuccessResult(loginUser);
        }
        else{
        	return ResultGenerator.genFailResult("用户名或密码错误！");
        }
        
    }

    @ApiOperation(value="校验token正确性", notes="")
    @RequestMapping(method = RequestMethod.POST,value="/token/check")
    public Result check(@RequestParam(required=true) String token) {
    	//用户的权限列表
    	UserAuthority ua =	userAuthorityService.getByUserId(1);
    	
        return ResultGenerator.genSuccessResult(ua);
    }

   

}
