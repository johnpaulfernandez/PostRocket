package org.perscholas.capstone.postrocket.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.perscholas.capstone.postrocket.dto.UserDTO;
import org.perscholas.capstone.postrocket.models.Role;
import org.perscholas.capstone.postrocket.models.User;
import org.perscholas.capstone.postrocket.repositories.RoleRepository;
import org.perscholas.capstone.postrocket.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private BCryptPasswordEncoder encoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private RoleServiceImpl roleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("test@example.com");
        userDTO.setPassword("encodedPassword");

        Role role = new Role();
        role.setName("FREE");
        roleService.saveRole(role);

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User user = modelMapper.map(userDTO, User.class);

        user.setRoles(Arrays.asList(role));

        when(encoder.encode(userDTO.getPassword())).thenReturn("encodedPassword");
        when(roleService.findRoleByRoleName("FREE")).thenReturn(role);

        userService.create(userDTO);

        assertThat(userDTO.getPassword()).isEqualTo("encodedPassword");
        assertThat(user.getRoles()).contains(role);
    }

    @Test
    public void testGetUserById() {

        User user = new User();
        user.setId(1);
        user.setEmail("test@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(1);

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isEqualTo(1);
        assertThat(foundUser.getEmail()).isEqualTo("test@example.com");
    }
}