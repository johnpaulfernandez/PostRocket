package org.perscholas.capstone.postrocket.services;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.perscholas.capstone.postrocket.dto.UserDTO;
import org.perscholas.capstone.postrocket.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();
    User getUserById(long id);

    User getUserByEmail(String email);
    void deleteUser(long id);

    void updateUser(User user);

    public void create(UserDTO userDTO);

    public UserDetails loadUserByUsername(String email);

    void deleteSessionAttribute(HttpSession session, String user);
}
