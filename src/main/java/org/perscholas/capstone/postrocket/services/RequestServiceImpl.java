package org.perscholas.capstone.postrocket.services;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.perscholas.capstone.postrocket.dto.UserDTO;
import org.perscholas.capstone.postrocket.models.Request;
import org.perscholas.capstone.postrocket.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    @Getter
    @Setter
    private String successUrl;

    @Autowired
    private RequestRepository requestRepository;

    @Override
    @Transactional
    public void saveRequest(Request request) {
        requestRepository.save(request);
    }

    @Override
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    @Override
    public Request getRequestById(int id) {
        return requestRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteRequest(int id) {
        Request request = requestRepository.findById(id).orElse(null);
        if (request != null) {
            requestRepository.delete(request);
        }
    }

    @Override
    public Request findRequestById(int id) {
        return requestRepository.findRequestById(id);
    }
}
