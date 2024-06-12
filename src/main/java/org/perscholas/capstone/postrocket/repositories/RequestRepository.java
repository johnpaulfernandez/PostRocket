package org.perscholas.capstone.postrocket.repositories;

import org.perscholas.capstone.postrocket.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    public Request findRequestById(int id);
    List<Request> findRequestsByUserId(int userId);
}
