package hr.algreba.pi.hardwareapp.controller;

import hr.algreba.pi.hardwareapp.command.HardwareCommand;
import hr.algreba.pi.hardwareapp.command.HardwareUpdateCommand;
import hr.algreba.pi.hardwareapp.command.ReviewCommand;
import hr.algreba.pi.hardwareapp.command.ReviewUpdateCommand;
import hr.algreba.pi.hardwareapp.dto.HardwareDTO;
import hr.algreba.pi.hardwareapp.dto.ReviewDTO;
import hr.algreba.pi.hardwareapp.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
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
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ReviewDTO save(@Valid @RequestBody final ReviewCommand command){
        return reviewService.save(command)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Review with the same id already exists"));
    }

    //@Secured("ROLE_ADMIN")
    @PutMapping
    public ReviewDTO update(@Valid @RequestBody final ReviewUpdateCommand updatedHardwareCommand){
        return reviewService.update(updatedHardwareCommand)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Review was not found by that id")
                );
    }

    //@Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        reviewService.deleteByID(id);
    }
}
