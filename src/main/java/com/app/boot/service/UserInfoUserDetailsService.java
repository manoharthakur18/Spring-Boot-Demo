package com.app.boot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.app.boot.dao.UserInfoRepository;
import com.app.boot.entities.UserInfo;
import com.app.boot.exceptionHandler.UserNotFoundException;
import com.app.boot.securityConfig.UserInfoToUserDetails;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

	@Autowired
	private UserInfoRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<UserInfo> userInfo = userRepository.findByName(username);
		return userInfo.map(UserInfoToUserDetails::new)
				.orElseThrow(() -> new UserNotFoundException("User not found" + username));

	}

}
