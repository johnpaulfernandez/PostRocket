//package org.perscholas.capstone.postrocket.services;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.perscholas.capstone.postrocket.models.GeneratedPost;
//import org.perscholas.capstone.postrocket.models.Request;
//import org.perscholas.capstone.postrocket.repositories.GeneratedPostRepository;
//import org.perscholas.capstone.postrocket.repositories.RequestRepository;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//class GeneratedPostServiceImplTest {
//
//    @Mock
//    private GeneratedPostRepository generatedPostRepository;
//
//    @InjectMocks
//    private GeneratedPostServiceImpl generatedPostService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void getGeneratedPostById() {
//        GeneratedPost post = new GeneratedPost();
//        post.setId(1000L);
//        post.setPost("This is a test tweet.");
//
//        when(generatedPostRepository.findById(1000L)).thenReturn(Optional.of(post));
//
//        GeneratedPost foundRequest = generatedPostService.getGeneratedPostById(1000L);
//
//        assertThat(foundRequest).isNotNull();
//        assertThat(foundRequest.getId()).isEqualTo(1000L);
//        assertThat(foundRequest.getPost()).isEqualTo("This is a test tweet.");
//    }
//}