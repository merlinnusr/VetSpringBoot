package com.vet.backend.services.imp;

import com.vet.backend.models.Role;
import com.vet.backend.repositories.RoleRepository;
import com.vet.backend.services.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        return this.roleRepository.save(role);
    }

    @Override
    public Role findRoleById(Long id) {
        return this.roleRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteRoleById(Long id) {
       if(this.findRoleById(id) == null){
           throw new RuntimeException("Role not found");
       }
        this.roleRepository.deleteById(id);
    }

    @Override
    public Role updateRole(Role role, Long id) {
        return null;
    }

    @Override
    public List<Role> findAllRoles() {
        return this.roleRepository.findAll();
    }
}
