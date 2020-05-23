package com.thoughtworks.authserver.seeding.service;

import com.thoughtworks.authserver.seeding.models.Permission;
import com.thoughtworks.authserver.seeding.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {
    @Autowired
    PermissionRepository permissionRepository;

    public Permission create(Permission permission) {
        return permissionRepository.save(permission);
    }

    public Permission findById(int id){
        return permissionRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
