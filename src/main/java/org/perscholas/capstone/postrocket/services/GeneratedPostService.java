package org.perscholas.capstone.postrocket.services;


import org.perscholas.capstone.postrocket.models.GeneratedPost;

import java.util.List;

public interface GeneratedPostService {
    void saveGeneratedPost(GeneratedPost generatedPost);
    List<GeneratedPost> getAllGeneratedPosts();
    GeneratedPost getGeneratedPostById(long id);
    void deleteGeneratedPost(long id);
    GeneratedPost findGeneratedPostById(long id);
}
