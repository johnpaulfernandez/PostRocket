package org.perscholas.capstone.postrocket.services;

import org.perscholas.capstone.postrocket.models.Role;

import java.util.List;

public interface RoleService {
    public Role saveRole(Role role);
    public Role findRoleByRoleName(String name);
    public List<Role> getAllRoles();
    public List<Role> getRolesByUser(long id);
}
