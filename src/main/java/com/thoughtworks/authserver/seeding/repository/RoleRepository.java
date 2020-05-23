package com.thoughtworks.authserver.seeding.repository;

import com.thoughtworks.authserver.seeding.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>  {
}
