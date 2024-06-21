//package org.perscholas.capstone.postrocket.repositories;
//
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.perscholas.capstone.postrocket.models.GeneratedPost;
//import org.perscholas.capstone.postrocket.models.Request;
//import org.perscholas.capstone.postrocket.models.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ActiveProfiles("test")
//class RequestRepositoryTest {
//
//    @Autowired
//    private RequestRepository requestRepository;
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
//    void findRequestById() {
//        // Given
//        Request request = new Request();
//        request.setName("Sample post");
//
//        // When
//        requestRepository.save(request);
//
//        // Then
//        Request foundRequest = requestRepository.findRequestById(request.getId());
//        assertThat(foundRequest).isNotNull();
//        assertThat(foundRequest.getName()).isEqualTo("Sample post");
//    }
//
//    @Test
//    void findRequestsByUserId() {
//        // Given
//        User user = new User();
//        user.setId(1);
//        user.setEmail("test@example.com");
//        user.setPassword("password");
//
//        userRepository.save(user);
//
//        Request request = new Request();
//        request.setName("Sample post");
//        request.setUser(userRepository.findUserByEmail("test@example.com"));
//
//        // When
//        requestRepository.save(request);
//
//        // Then
//        List<Request> foundRequest = requestRepository.findRequestsByUserId(userRepository.findUserByEmail("test@example.com").getId());
//        assertThat(foundRequest).isNotNull();
//        assertThat(foundRequest).contains(request);
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = "Post 1,Post 2,Post 3")
//    void findRequestsByUserIdAsc(String posts) {
//        // Given
//        User user = new User();
//        user.setId(2);
//        user.setEmail("test_asc@example.com");
//        user.setPassword("password");
//
//        userRepository.save(user);
//
//        List<String> inputs = Arrays.asList(posts.split(","));
//        List<Request> requests = new ArrayList<>();
//
//        // When
//        inputs.forEach(input -> {
//            Request request = new Request();
//            request.setName(input);
//            request.setUser(userRepository.findUserByEmail("test_asc@example.com"));
//            requestRepository.save(request);
//            requests.add(request);
//        });
//
//        // Then
//        List<Request> foundRequest = requestRepository.findRequestsByUserIdAsc(userRepository.findUserByEmail("test_asc@example.com").getId());
//        assertThat(foundRequest).isNotNull();
//        assertThat(foundRequest.containsAll(requests)).isTrue();
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = "Post 1,Post 2,Post 3")
//    void findRequestsByUserIdDesc(String posts) {
//        // Given
//        User user = new User();
//        user.setId(3);
//        user.setEmail("test_desc@example.com");
//        user.setPassword("password");
//
//        userRepository.save(user);
//
//        List<String> inputs = Arrays.asList(posts.split(","));
//
//        List<Request> requests = new ArrayList<>();
//
//        // When
//        Collections.reverse(inputs);
//
//        inputs.forEach(input -> {
//            Request request = new Request();
//            request.setName(input);
//            request.setUser(userRepository.findUserByEmail("test_desc@example.com"));
//            requestRepository.save(request);
//            requests.add(request);
//        });
//
//        // Then
//        List<Request> foundRequest = requestRepository.findRequestsByUserIdDesc(userRepository.findUserByEmail("test_desc@example.com").getId());
//        assertThat(foundRequest).isNotNull();
//        assertEquals(foundRequest.size(), requests.size());
//        assertThat(foundRequest.containsAll(requests)).isTrue();
//
//        for (int i = 0; i < requests.size(); i++) {
//            assertEquals(requests.get(i).getName(), foundRequest.get(i).getName());
//        }
//    }
//}