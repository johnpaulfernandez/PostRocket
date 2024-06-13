package org.perscholas.capstone.postrocket.repositories;

import org.perscholas.capstone.postrocket.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    public Request findRequestById(long id);
    @Query(value = "select * from request where request.user_id = (select id from user where id = :userId)", nativeQuery = true)
    List<Request> findRequestsByUserId(long userId);

    @Query(value = "select * from request where request.user_id = (select id from user where id = :userId) ORDER BY name ASC", nativeQuery = true)
    List<Request> findRequestsByUserIdAsc(@Param("userId") long userId);

    @Query(value = "select * from request where request.user_id = (select id from user where id = :userId) ORDER BY name DESC", nativeQuery = true)
    List<Request> findRequestsByUserIdDesc(@Param("userId") long userId);
}



