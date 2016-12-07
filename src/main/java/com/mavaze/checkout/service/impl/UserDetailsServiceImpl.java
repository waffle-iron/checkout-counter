package com.mavaze.checkout.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mavaze.checkout.config.ActorDetails;
import com.mavaze.checkout.domain.Actor;
import com.mavaze.checkout.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public ActorDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Actor user = userRepository.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Username " + userName + " not found");
		}
		return new ActorDetails(user);
	}

}