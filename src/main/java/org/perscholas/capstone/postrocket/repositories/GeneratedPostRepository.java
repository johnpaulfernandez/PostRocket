package org.perscholas.capstone.postrocket.repositories;

import org.perscholas.capstone.postrocket.models.GeneratedPost;
import org.perscholas.capstone.postrocket.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GeneratedPostRepository extends JpaRepository<GeneratedPost, Integer> {
    public GeneratedPost findGeneratedPostById(int id);
}
