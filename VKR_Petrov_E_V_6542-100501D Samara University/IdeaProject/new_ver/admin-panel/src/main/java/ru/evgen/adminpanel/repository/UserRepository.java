package ru.evgen.adminpanel.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.evgen.adminpanel.entity.User;

import java.util.UUID;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, UUID> {
    public User getUserById(UUID uuid);
    public User getUserByEmail(String str);
}
