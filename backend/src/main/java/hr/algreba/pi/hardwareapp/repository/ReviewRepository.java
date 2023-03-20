package hr.algreba.pi.hardwareapp.repository;

import hr.algreba.pi.hardwareapp.domain.Hardware;
import hr.algreba.pi.hardwareapp.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Set<Review> findAllByHardware_Code(String hardwareCode);

    void deleteAllByHardware_Code(String hardwareCode);

/*    Optional<Review> save(Review review);

    Optional<Review> update(String id, Review updatedReview);

    void deleteByID(String id);*/

}
