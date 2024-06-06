package org.perscholas.capstone.postrocket.repositories;

import org.perscholas.capstone.postrocket.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findUserByEmail(String email);
}
