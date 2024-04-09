package javau9.securingjpa;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

	
	UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		AppUser user = userRepository.findByUserName(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User not found : " + username);
		}
		
		return User.builder()
				.username(user.getUserName())
				.password(user.getPassword())
				.roles(user.getRole())
				.build();
	}
	
}
