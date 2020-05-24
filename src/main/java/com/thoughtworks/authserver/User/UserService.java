package com.thoughtworks.authserver.User;

import com.thoughtworks.authserver.User.User;
import com.thoughtworks.authserver.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public User findById(int id){
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public User findByEmailId(String emailId){
        return userRepository.findByEmailId(emailId).orElseThrow(RuntimeException::new);
    }

}
