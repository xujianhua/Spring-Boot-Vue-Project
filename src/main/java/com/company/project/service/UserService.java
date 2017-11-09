package com.company.project.service;
import com.company.project.model.User;
import com.company.project.core.Service;


/**
 * Created by CodeGenerator on 2017/10/26.
 */
public interface UserService extends Service<User> {
	
	User register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}
