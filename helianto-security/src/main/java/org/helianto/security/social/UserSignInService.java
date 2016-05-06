package org.helianto.security.social;

import javax.inject.Inject;

import org.helianto.security.internal.UserDetailsAdapter;
import org.helianto.security.service.AuthorizationChecker;
import org.helianto.security.util.SignInUtils;
import org.helianto.user.repository.UserReadAdapter;
import org.helianto.user.repository.UserRepository;

/**
 * Required to sign in users given the user id.
 *  
 * @author mauriciofernandesdecastro
 */
public class UserSignInService {
	
	@Inject
	private UserRepository userRepository;
	
	@Inject
	private AuthorizationChecker authorizationChecker;
	
	public void signin(Integer userId){
		UserReadAdapter user = userRepository.findByUserId(userId);
		userRepository.save(userRepository.findOne(userId).updateLastEvent());
		UserDetailsAdapter userDetails = new UserDetailsAdapter(user);
		SignInUtils.signin(authorizationChecker.updateAuthorities(userDetails));
	}

}
