package org.perscholas.capstone.postrocket.repositories;

import org.perscholas.capstone.postrocket.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    public Request findRequestById(long id);
    List<Request> findRequestsByUserId(long userId);
}
