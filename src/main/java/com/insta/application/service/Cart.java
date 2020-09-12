package com.insta.application.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.insta.application.model.Role;
import com.insta.application.model.User;
import com.insta.application.repository.RoleRepo;
import com.insta.application.repository.UserRepo;

@Service
public class UserService 
{
	@Autowired
	UserRepo uRepo;

	@Autowired
	RoleRepo rRepo;
	
	public User createUser(String username, String password, String role) throws Exception 
	{
		User user = new User();
		
		Role roleEntity = rRepo.findByName(role);
		Set<Role> roleset = new HashSet<Role>();
		roleset.add(roleEntity);
		user.setUserName(username);
		user.setStatus(true);
		user.setRoles(roleset);
		user.setPassword(bCryptPassword(password));
		user.setPasswordExpired(false);
		uRepo.save(user);
		return user;
	}

	public String bCryptPassword(String password)
	{
		String bcyptedPassword;
		bcyptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		return bcyptedPassword;
	}

	public void updateUser(String username, String password) throws Exception 
	{
		ArrayList<User> users = uRepo.findUserByUsername(username);
		if(users.size()==1)
		{
			User user = users.get(0);
			user.setPassword(bCryptPassword(password));
			uRepo.save(user);
		}
		else 
		{
			throw new Exception ("No or Multiple users found by username!");
		}
		}
	}
