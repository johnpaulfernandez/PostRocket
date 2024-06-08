package org.perscholas.capstone.postrocket.services;


import org.perscholas.capstone.postrocket.models.Request;

import java.util.List;

public interface RequestService {
    void saveRequest(Request request);
    List<Request> getAllRequests();
    Request getRequestById(int id);
    void deleteRequest(int id);
    Request findRequestById(int id);
}
