package com.company.project.service.impl;

import com.company.project.dao.UserAuthorityMapper;
import com.company.project.model.UserAuthority;
import com.company.project.service.UserAuthorityService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/10/27.
 */
@Service
@Transactional
public class UserAuthorityServiceImpl implements UserAuthorityService {
    @Resource
    private UserAuthorityMapper userAuthorityMapper;

    public UserAuthority getByUserId(Integer userId){
    	return userAuthorityMapper.getByUserId(userId);
    }
}
