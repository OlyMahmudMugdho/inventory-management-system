package com.OlyMahmudMugdho.inventorymanagementsystem.services.impl;

import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.User;
import com.OlyMahmudMugdho.inventorymanagementsystem.repositories.UserRepository;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User addUser(User user) {

        userRepository.save(user);
        return user;
    }
}
