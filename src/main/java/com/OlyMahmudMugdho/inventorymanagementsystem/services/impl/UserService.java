package com.OlyMahmudMugdho.inventorymanagementsystem.services.impl;

import com.OlyMahmudMugdho.inventorymanagementsystem.models.entities.User;
import com.OlyMahmudMugdho.inventorymanagementsystem.repositories.RoleRepository;
import com.OlyMahmudMugdho.inventorymanagementsystem.repositories.UserRepository;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    private UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    @Override
    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }

    public Page<User> getAllUsers(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    public Optional<User> getUserById(long id) {
        return userRepository.findById(id);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
