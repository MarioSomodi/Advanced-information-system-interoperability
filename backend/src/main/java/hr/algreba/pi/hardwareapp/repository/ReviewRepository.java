package hr.algreba.pi.hardwareapp.repository;

import hr.algreba.pi.hardwareapp.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Set<Review> findAllByHardware_Code(String hardwareCode);

    void deleteAllByHardware_Code(String hardwareCode);

}
