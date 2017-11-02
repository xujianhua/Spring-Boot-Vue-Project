package com.company.project.service.impl;

import com.company.project.dao.AuthorityMapper;
import com.company.project.model.Authority;
import com.company.project.service.AuthorityService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/10/27.
 */
@Service
@Transactional
public class AuthorityServiceImpl extends AbstractService<Authority> implements AuthorityService {
    @Resource
    private AuthorityMapper authorityMapper;

}
