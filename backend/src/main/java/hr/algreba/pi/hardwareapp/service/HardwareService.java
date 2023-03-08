package hr.algreba.pi.hardwareapp.service;

import hr.algreba.pi.hardwareapp.command.HardwareCommand;
import hr.algreba.pi.hardwareapp.command.HardwareUpdateCommand;
import hr.algreba.pi.hardwareapp.dto.HardwareDTO;

import java.util.List;
import java.util.Optional;

public interface HardwareService {

    List<HardwareDTO> findAll();

    Optional<HardwareDTO> findByCode(String code);

    Optional<HardwareDTO> save(HardwareCommand hardwareCommand);

    Optional<HardwareDTO> update(String code, HardwareUpdateCommand updatedHardwareCommand);

    void deleteByCode(String code);

}
