package hr.algreba.pi.hardwareapp.service;

import hr.algreba.pi.hardwareapp.dto.ReviewDTO;
import hr.algreba.pi.hardwareapp.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Set<ReviewDTO> findAll() {
        return reviewRepository.findAll().stream().map(ReviewDTO::new).collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public Set<ReviewDTO> findAllByHardwareCode(String hardwareCode) {
        return reviewRepository.findAllByHardware_Code(hardwareCode).stream().map(ReviewDTO::new).collect(Collectors.toUnmodifiableSet());
    }
}
