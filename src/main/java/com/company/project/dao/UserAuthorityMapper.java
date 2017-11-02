package com.company.project.dao;

import org.apache.ibatis.annotations.Param;

import com.company.project.model.UserAuthority;

public interface UserAuthorityMapper {
	
	UserAuthority getByUserId(@Param("userId") Integer userId);
	
}