package hr.algreba.pi.hardwareapp.controller;

import hr.algreba.pi.hardwareapp.dto.ReviewDTO;
import hr.algreba.pi.hardwareapp.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("review")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public Set<ReviewDTO> getAllReviews() {
        return reviewService.findAll();
    }

    @GetMapping(params = "hardwareCode")
    public Set<ReviewDTO> getAllReviewsByHardwareCode(@RequestParam String hardwareCode) {
        return reviewService.findAllByHardwareCode(hardwareCode);
    }

}
