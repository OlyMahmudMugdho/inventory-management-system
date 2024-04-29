package com.OlyMahmudMugdho.inventorymanagementsystem.controllers;

import com.OlyMahmudMugdho.inventorymanagementsystem.mappers.impl.RoleMapper;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.Role;
import com.OlyMahmudMugdho.inventorymanagementsystem.models.dto.RoleDto;
import com.OlyMahmudMugdho.inventorymanagementsystem.services.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/roles")
public class RoleController {
    private static final Logger log = LoggerFactory.getLogger(RoleController.class);
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

    @PostMapping("/add")
    public String addRole(@ModelAttribute("role") RoleDto roleDto) {
        Role role = roleMapper.mapFrom(roleDto);
        roleService.createRole(role);
        return "redirect:/roles";
    }

    @GetMapping("/{id}")
    public String getRoleDeatils(@PathVariable("id") int id, Model model) {
        Optional<Role> role = roleService.getRoleById(id);
        if (role.isPresent()) {
            model.addAttribute("role", role.get());
        }
        return "roles/role-details-page";
    }

    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable("id") int id) {
        Optional<Role> role = roleService.getRoleById(id);
        if (role.isPresent()) {
            log.info(role.get().toString());
            System.out.println(role.get().toString());
            roleService.deleteRoleById(id);
        }
        return "redirect:/roles";
    }
}
