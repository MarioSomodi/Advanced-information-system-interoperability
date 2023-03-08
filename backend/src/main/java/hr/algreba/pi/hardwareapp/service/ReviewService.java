package hr.algreba.pi.hardwareapp.service;

import hr.algreba.pi.hardwareapp.dto.ReviewDTO;

import java.util.Set;

public interface ReviewService {

    Set<ReviewDTO> findAll();

    Set<ReviewDTO> findAllByHardwareCode(String hardwareCode);

}
