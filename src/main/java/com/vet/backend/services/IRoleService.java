package com.vet.backend.services;

import com.vet.backend.models.Role;

import java.util.List;

public interface IRoleService {

    //create role
    public Role save(Role role);
    //find role by id
    public Role findRoleById(Long id);
    //delete role
    public void deleteRoleById(Long id);
    //update role
    public Role updateRole(Role role, Long id);
    //get all roles
    public List<Role> findAllRoles();


}
