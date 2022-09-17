package pp.security_bootstrap.service;

import pp.security_bootstrap.model.Role;

public interface RoleService {
    Role findRoleByName(String name);
}
