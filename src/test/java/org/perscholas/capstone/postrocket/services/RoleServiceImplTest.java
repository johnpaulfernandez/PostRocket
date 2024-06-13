package org.perscholas.capstone.postrocket.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.perscholas.capstone.postrocket.models.Role;
import org.perscholas.capstone.postrocket.models.Role;
import org.perscholas.capstone.postrocket.models.Role;
import org.perscholas.capstone.postrocket.models.User;
import org.perscholas.capstone.postrocket.repositories.RoleRepository;
import org.perscholas.capstone.postrocket.repositories.RoleRepository;
import org.perscholas.capstone.postrocket.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RoleServiceImplTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RoleServiceImpl roleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findRoleById() {
        Role role = new Role();
        role.setId(10L);
        role.setName("Role 1");

        when(roleRepository.findRoleById(10L)).thenReturn(role);

        Role foundRole = roleService.findRoleById(10L);

        assertThat(foundRole).isNotNull();
        assertThat(foundRole.getId()).isEqualTo(10L);
        assertThat(foundRole.getName()).isEqualTo("Role 1");
    }

    @Test
    void findRoleByName() {
        Role role = new Role();
        role.setId(10L);
        role.setName("Role 1");

        when(roleRepository.findRoleByName("Role 1")).thenReturn(role);

        Role foundRole = roleService.findRoleByName("Role 1");

        assertThat(foundRole).isNotNull();
        assertThat(foundRole.getId()).isEqualTo(10L);
        assertThat(foundRole.getName()).isEqualTo("Role 1");
    }
}