package hr.algreba.pi.hardwareapp.repository;

import hr.algreba.pi.hardwareapp.domain.Hardware;

import java.util.Optional;
import java.util.Set;

public interface HardwareRepository {

    Set<Hardware> findAll();

    Optional<Hardware> findByCode(String code);

    Optional<Hardware> save(Hardware hardware);

    Optional<Hardware> update(String code, Hardware updatedHardware);

    void deleteByCode(String code);

}
