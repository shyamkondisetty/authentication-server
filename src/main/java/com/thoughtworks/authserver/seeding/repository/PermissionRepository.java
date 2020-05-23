package com.thoughtworks.authserver.seeding.repository;

import com.thoughtworks.authserver.seeding.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
}
