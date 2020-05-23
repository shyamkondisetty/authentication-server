package com.thoughtworks.authserver.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.thoughtworks.authserver.seeding.models.Permission;
import com.thoughtworks.authserver.seeding.models.Role;
import com.thoughtworks.authserver.seeding.models.User;
import com.thoughtworks.authserver.seeding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.thoughtworks.authserver.model.UserEntity;

@Repository
public class OAuthDAOServiceImpl implements OAuthDAOService {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserService userService;

    @Override
    public UserEntity getUserDetails(String emailId) {

        Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();

        User user = userService.findByEmailId(emailId);
        UserEntity userEntity = new UserEntity();
        userEntity.setEmailId(emailId);
        userEntity.setId(user.getId()+"");
        userEntity.setName(user.getName());
        userEntity.setPassword(user.getPassword());

        List<Role> rolesList = user.getRoles();
        List<String> permissionsList=new ArrayList<>();

        for(int i=0;i<rolesList.size();i++){
            Role role=rolesList.get(i);
            for (int j=0;j<role.getPermissions().size();j++){
                permissionsList.add("ROLE"+role.getPermissions().get(j).getName());
            }
        }

        if (permissionsList != null && !permissionsList.isEmpty()) {
            for (String permission : permissionsList) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission);
                grantedAuthoritiesList.add(grantedAuthority);
            }
            userEntity.setGrantedAuthoritiesList(grantedAuthoritiesList);
        }
        return userEntity;
    }
}

