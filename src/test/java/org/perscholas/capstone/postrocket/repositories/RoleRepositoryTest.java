//package org.perscholas.capstone.postrocket.repositories;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.convention.MatchingStrategies;
//import org.perscholas.capstone.postrocket.dto.UserDTO;
//import org.perscholas.capstone.postrocket.models.Role;
//import org.perscholas.capstone.postrocket.models.User;
//import org.perscholas.capstone.postrocket.services.RoleServiceImpl;
//import org.perscholas.capstone.postrocket.services.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ActiveProfiles("test")
//public class RoleRepositoryTest {
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @BeforeAll
//    public static void createDatabase() {
//        try (Connection connection = DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/?user=teamlead&password=teamlead_password");
//             Statement statement = connection.createStatement()) {
//            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS testdb");
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to create test database", e);
//        }
//    }
//
//    @AfterAll
//    public static void dropDatabase() {
//        try (Connection connection = DriverManager.getConnection(
//                "jdbc:mysql://localhost:3306/?user=teamlead&password=teamlead_password");
//             Statement statement = connection.createStatement()) {
//            statement.executeUpdate("DROP DATABASE IF EXISTS testdb");
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to drop test database", e);
//        }
//    }
//
//    @Test
//    public void testFindRoleByName() {
//        // Given
//        Role role = new Role();
//        role.setName("Test_Role");
//
//        // When
//        roleRepository.save(role);
//
//        // Then
//        Role foundRole = roleRepository.findRoleByName(role.getName());
//        assertThat(foundRole).isNotNull();
//        assertThat(foundRole.getName()).isEqualTo("Test_Role");
//    }
//
//    @Test
//    public void testSaveAndFindRoleByUserID() {
//        // Given
//        Role role = new Role();
//        role.setName("Free");
//
//        // When
//        roleRepository.save(role);
//
//        User user = new User();
//        user.setId(1L);
//        user.setEmail("test@example.com");
//        user.setPassword("password");
//        user.setRoles(Arrays.asList(role));
//
//        userRepository.save(user);
//
//        // Then
//        Role foundRole = roleRepository.findRoleById(1L);
//        assertThat(foundRole).isNotNull();
//        assertThat(foundRole.getName()).isEqualTo("Free");
//    }
//}
