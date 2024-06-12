package org.perscholas.capstone.postrocket.services;


import org.perscholas.capstone.postrocket.models.Request;

import java.util.List;

public interface RequestService {
    void saveRequest(Request request);
    List<Request> getAllRequests();
    Request getRequestById(long id);
    void deleteRequest(long id);
    Request findRequestById(long id);
    List<Request> getRequestsByUserId(long userId);

}
