package com.optica.backend_crm.services;

import com.optica.backend_crm.models.Role;
import com.optica.backend_crm.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role>  getAllRoles() {
        return roleRepository.findAll();
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public void deleteRole(Integer id_role) {
        roleRepository.deleteById(id_role);
    }
}
