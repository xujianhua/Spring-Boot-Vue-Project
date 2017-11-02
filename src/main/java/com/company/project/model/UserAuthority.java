package com.company.project.model;

import java.util.List;

import com.company.project.model.Authority;
import com.company.project.model.User;

public class UserAuthority {
  
    private User user;
    
    private List<Authority> authorityList;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Authority> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<Authority> authorityList) {
		this.authorityList = authorityList;
	}

	

}