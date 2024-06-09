package org.perscholas.capstone.postrocket.services;

import jakarta.transaction.Transactional;
import org.perscholas.capstone.postrocket.dto.UserDTO;
import org.perscholas.capstone.postrocket.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();
    User getUserById(int id);

    User getUserByEmail(String email);
    void deleteUser(int id);

    void updateUser(User user);

    public void create(UserDTO userDTO);

    public UserDetails loadUserByUsername(String email);
}
