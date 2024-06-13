package org.perscholas.capstone.postrocket.services;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.perscholas.capstone.postrocket.dto.UserDTO;
import org.perscholas.capstone.postrocket.models.Request;
import org.perscholas.capstone.postrocket.models.Role;
import org.perscholas.capstone.postrocket.models.User;
import org.perscholas.capstone.postrocket.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

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
    public Request getRequestById(long id) {
        return requestRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteRequest(long id) {
        Request request = requestRepository.findById(id).orElse(null);
        if (request != null) {
            requestRepository.delete(request);
        }
    }

    @Override
    public Request findRequestById(long id) {
        return requestRepository.findRequestById(id);
    }

    @Override
    public List<Request> getRequestsByUserId(long userId) {
        return requestRepository.findRequestsByUserId(userId);
    }

    @Override
    public List<Request> getRequestsByUserIdAsc(long userId) {
        return requestRepository.findRequestsByUserIdAsc(userId);
    }

    @Override
    public List<Request> getRequestsByUserIdDesc(long userId) {
        return requestRepository.findRequestsByUserIdDesc(userId);
    }
}
