package org.perscholas.capstone.postrocket.services;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.perscholas.capstone.postrocket.models.GeneratedPost;
import org.perscholas.capstone.postrocket.repositories.GeneratedPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneratedPostServiceImpl implements GeneratedPostService {

    @Autowired
    private GeneratedPostRepository generatedPostRepository;

    @Override
    @Transactional
    public void saveGeneratedPost(GeneratedPost generatedPost) {

        generatedPostRepository.save(generatedPost);
    }

    @Override
    public List<GeneratedPost> getAllGeneratedPosts() {
        return generatedPostRepository.findAll();
    }

    @Override
    public GeneratedPost getGeneratedPostById(int id) {
        return generatedPostRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteGeneratedPost(int id) {
        GeneratedPost generatedPost = generatedPostRepository.findById(id).orElse(null);
        if (generatedPost != null) {
            generatedPostRepository.delete(generatedPost);
        }
    }

    @Override
    public GeneratedPost findGeneratedPostById(int id) {
        return generatedPostRepository.findGeneratedPostById(id);
    }
}
