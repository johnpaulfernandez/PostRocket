package org.perscholas.capstone.postrocket.services;


import org.perscholas.capstone.postrocket.models.GeneratedPost;

import java.util.List;

public interface GeneratedPostService {
    void saveGeneratedPost(GeneratedPost generatedPost);
    List<GeneratedPost> getAllGeneratedPosts();
    GeneratedPost getGeneratedPostById(int id);
    void deleteGeneratedPost(int id);
    GeneratedPost findGeneratedPostById(int id);
}
