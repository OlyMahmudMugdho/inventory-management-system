package com.OlyMahmudMugdho.inventorymanagementsystem.services;

import com.OlyMahmudMugdho.inventorymanagementsystem.repositories.CategoryRepository;
import com.OlyMahmudMugdho.inventorymanagementsystem.repositories.ProductRepository;
import com.OlyMahmudMugdho.inventorymanagementsystem.repositories.RoleRepository;
import com.OlyMahmudMugdho.inventorymanagementsystem.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class GlobalService {
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private RoleRepository roleRepository;

    public GlobalService(UserRepository userRepository,
                         ProductRepository productRepository,
                         CategoryRepository categoryRepository,
                         RoleRepository roleRepository)
    {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.roleRepository = roleRepository;
    }
    
    public long userCount(){
        return userRepository.count();
    }
    

}
