/*
 * Copyright 2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.helianto.core.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Some static helper methods related to User details.
 * 
 * @author mauriciofernandesdecastro
 */
public final class UserDetailsUtils {

	/**
	 * Programmatically sign-in the member holding the provided UserDetailsAdapter.
	 * Sets the UserDetailsAdapter in the SecurityContext, which will associate this UserDetailsAdapter with the user Session.
	 */
	public static void signin(UserDetailsAdapter userDetailsAdapter) {
		SecurityContextHolder.getContext().setAuthentication(authenticationTokenFor(userDetailsAdapter));
	}
	
	/**
	 * Construct a Spring Security Authentication token.
	 */
	public static Authentication authenticationTokenFor(UserDetailsAdapter userDetailsAdapter) {
		return new UsernamePasswordAuthenticationToken(userDetailsAdapter, null, userDetailsAdapter.getAuthorities());		
	}
	
	/**
	 * Get the currently authenticated UserDetailsAdapter principal.
	 */
	public static UserDetailsAdapter getCurrentUserAdapter() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}
		Object principal = authentication.getPrincipal();
		return principal instanceof UserDetailsAdapter ? (UserDetailsAdapter) principal : null;
	}
	
	private UserDetailsUtils() {
	}
}
