package com.mavaze.checkout.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.mavaze.checkout.domain.Actor;

public class ActorDetails extends User {

	private static final long serialVersionUID = 6671379268499333704L;
	
	private final Actor user;
	
	public ActorDetails(Actor user) {
		super(user.getUserName(), user.getPasswordHash(), getGrantedAuthorities(user));
		this.user = user;
	}
	
	public Actor getUser() {
		return this.user;
	}
	
	private static Collection<? extends GrantedAuthority> getGrantedAuthorities(Actor user) {
		Collection<? extends GrantedAuthority> authorities;
		authorities = Collections.singletonList(new GrantedAuthority() {
			private static final long serialVersionUID = -3432015570405520355L;
			@Override public String getAuthority() {
				return "ROLE_" + user.getRole().toString();
			}
		});
		return authorities;
	}
}
