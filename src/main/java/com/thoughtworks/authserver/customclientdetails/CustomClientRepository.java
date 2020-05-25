package com.thoughtworks.authserver.customclientdetails;

import com.thoughtworks.authserver.Role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomClientRepository extends JpaRepository<CustomClient, String> {
}
