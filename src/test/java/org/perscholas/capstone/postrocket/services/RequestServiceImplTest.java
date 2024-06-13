package org.perscholas.capstone.postrocket.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.perscholas.capstone.postrocket.dto.UserDTO;
import org.perscholas.capstone.postrocket.models.Request;
import org.perscholas.capstone.postrocket.models.Role;
import org.perscholas.capstone.postrocket.models.User;
import org.perscholas.capstone.postrocket.repositories.RequestRepository;
import org.perscholas.capstone.postrocket.repositories.RoleRepository;
import org.perscholas.capstone.postrocket.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RequestServiceImplTest {

    @Mock
    private RequestRepository requestRepository;

    @InjectMocks
    private RequestServiceImpl requestService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getRequestById() {
        Request request = new Request();
        request.setId(10L);
        request.setName("Request 1");

        when(requestRepository.findById(10L)).thenReturn(Optional.of(request));

        Request foundRequest = requestService.getRequestById(10L);

        assertThat(foundRequest).isNotNull();
        assertThat(foundRequest.getId()).isEqualTo(10L);
        assertThat(foundRequest.getName()).isEqualTo("Request 1");
    }
}