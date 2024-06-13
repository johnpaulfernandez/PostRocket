package org.perscholas.capstone.postrocket.repositories;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.perscholas.capstone.postrocket.models.GeneratedPost;
import org.perscholas.capstone.postrocket.models.Role;
import org.perscholas.capstone.postrocket.services.RoleServiceImpl;
import org.perscholas.capstone.postrocket.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class GeneratedPostRepositoryTest {

    @Autowired
    private GeneratedPostRepository postRepository;

    @BeforeAll
    public static void createDatabase() {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/?user=teamlead&password=teamlead_password");
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS testdb");
        } catch (Exception e) {
            throw new RuntimeException("Failed to create test database", e);
        }
    }

    @AfterAll
    public static void dropDatabase() {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/?user=teamlead&password=teamlead_password");
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP DATABASE IF EXISTS testdb");
        } catch (Exception e) {
            throw new RuntimeException("Failed to drop test database", e);
        }
    }

    @Test
    public void testFindGeneratedPostById() {
        // Given
        GeneratedPost post = new GeneratedPost();
        post.setPost("Sample post");

        // When
        postRepository.save(post);

        // Then
        GeneratedPost foundPost = postRepository.findGeneratedPostById(post.getId());
        assertThat(foundPost).isNotNull();
        assertThat(foundPost.getPost()).isEqualTo("Sample post");
    }

}