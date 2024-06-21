//package org.perscholas.capstone.postrocket.repositories;
//
//import org.junit.jupiter.api.*;
//import org.perscholas.capstone.postrocket.models.Role;
//import org.perscholas.capstone.postrocket.models.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ActiveProfiles("test")
//class UserRepositoryTest {
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
//    public void findUserByEmail() {
//        // Given
//        User user = new User();
//        user.setEmail("testUser@gmail.com");
//
//        // When
//        userRepository.save(user);
//
//        // Then
//        User foundUser = userRepository.findUserByEmail(user.getEmail());
//        assertThat(foundUser).isNotNull();
//        assertThat(foundUser.getEmail()).isEqualTo("testUser@gmail.com");
//    }
//}