package ru.evgen.adminpanel.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.evgen.adminpanel.entity.Role;

import java.util.UUID;

@Repository
@Transactional
public interface RoleRepository extends CrudRepository<Role, UUID> {
    public Role getRoleById(UUID uuid);
}
