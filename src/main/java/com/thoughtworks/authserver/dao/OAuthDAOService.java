package com.thoughtworks.authserver.dao;

import com.thoughtworks.authserver.model.UserEntity;

public interface OAuthDAOService {

	public UserEntity getUserDetails(String emailId);
}
