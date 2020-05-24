package com.thoughtworks.authserver.customuserdetails;

import com.thoughtworks.authserver.Role.Role;
import com.thoughtworks.authserver.User.User;
import com.thoughtworks.authserver.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {
			Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();

			User user = userService.findByEmailId(username);
			UserEntity userEntity = new UserEntity();
			userEntity.setEmailId(user.getEmailId());
			userEntity.setId(user.getId()+"");
			userEntity.setName(user.getName());
			userEntity.setPassword(user.getPassword());

			List<Role> rolesList = user.getRoles();
			List<String> permissionsList=new ArrayList<>();

			for(int i=0;i<rolesList.size();i++){
				Role role=rolesList.get(i);
				for (int j=0;j<role.getPermissions().size();j++){
					permissionsList.add("ROLE_"+role.getPermissions().get(j).getName());
				}
			}

			if (permissionsList != null && !permissionsList.isEmpty()) {
				for (String permission : permissionsList) {
					GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission);
					grantedAuthoritiesList.add(grantedAuthority);
				}
				userEntity.setGrantedAuthoritiesList(grantedAuthoritiesList);
			}

			CustomUser customUser = new CustomUser(userEntity);
			return customUser;

		} catch (Exception e) {
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}

	}

}
