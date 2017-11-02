package com.company.project.service;
import com.company.project.model.UserAuthority;

import org.apache.ibatis.annotations.Param;



/**
 * Created by CodeGenerator on 2017/10/27.
 */
public interface UserAuthorityService {
	UserAuthority getByUserId(Integer userId);
}
