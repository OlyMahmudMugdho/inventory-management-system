package com.OlyMahmudMugdho.inventorymanagementsystem.controllers;

import com.OlyMahmudMugdho.inventorymanagementsystem.mappers.impl.RoleMapper;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.Role;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.dto.RoleDto;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;
    private RoleMapper roleMapper;

    public RoleController(RoleService roleService, RoleMapper roleMapper) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
    }

    @GetMapping
    public String rolesPage(Model model) {
        List<Role> fetchedRoles = roleService.getAllRoles();
        List<RoleDto> roles = fetchedRoles.stream().map(r -> roleMapper.mapTo(r)).toList();
        model.addAttribute("roles", roles);
        return "roles/all-roles-page";
    }

    @GetMapping("/add-role")
    public String addRolePage(Model model) {
        model.addAttribute("role", new RoleDto());
        return "roles/add-role-page";
    }

    @PostMapping
    public String addRole(@ModelAttribute("role") RoleDto roleDto) {
        Role role = roleMapper.mapFrom(roleDto);
        roleService.createRole(role);
        return "redirect:/roles";
    }
}
