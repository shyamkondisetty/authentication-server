package com.thoughtworks.authserver.Role;

import com.thoughtworks.authserver.Role.Role;
import com.thoughtworks.authserver.Role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Role create(Role role) {
        return roleRepository.save(role);
    }

    public Role findById(int id){
        return roleRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
