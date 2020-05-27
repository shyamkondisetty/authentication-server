package com.thoughtworks.authserver.customclientdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomClientService implements ClientDetailsService {
    @Autowired
    CustomClientRepository customClientRepository;

    public CustomClient create(CustomClient customClient) {
        return customClientRepository.save(customClient);
    }


    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Optional<CustomClient> customClient = customClientRepository.findById(clientId);
        if (!customClient.isPresent()) {
            throw new ClientRegistrationException("client is invalid");
        }
        return customClient.get();
    }

}
