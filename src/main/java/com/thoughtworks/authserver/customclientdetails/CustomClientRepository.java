package com.thoughtworks.authserver.customclientdetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomClientRepository extends JpaRepository<CustomClient, String> {
}
