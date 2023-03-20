package hr.algreba.pi.hardwareapp.service;

import hr.algreba.pi.hardwareapp.command.HardwareCommand;
import hr.algreba.pi.hardwareapp.command.HardwareUpdateCommand;
import hr.algreba.pi.hardwareapp.command.ReviewCommand;
import hr.algreba.pi.hardwareapp.command.ReviewUpdateCommand;
import hr.algreba.pi.hardwareapp.dto.HardwareDTO;
import hr.algreba.pi.hardwareapp.dto.ReviewDTO;

import java.util.Optional;
import java.util.Set;

public interface ReviewService {

    Set<ReviewDTO> findAll();
    Set<ReviewDTO> findAllByHardwareCode(String hardwareCode);
    Optional<ReviewDTO> save(ReviewCommand reviewCommand);
    Optional<ReviewDTO> update(ReviewUpdateCommand updatedReviewCommand);
    void deleteByID(Long id);
}
