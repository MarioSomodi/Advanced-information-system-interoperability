package hr.algreba.pi.hardwareapp.service;

import hr.algreba.pi.hardwareapp.command.HardwareCommand;
import hr.algreba.pi.hardwareapp.command.HardwareUpdateCommand;
import hr.algreba.pi.hardwareapp.domain.Hardware;
import hr.algreba.pi.hardwareapp.dto.HardwareDTO;
import hr.algreba.pi.hardwareapp.repository.HardwareRepository;
import hr.algreba.pi.hardwareapp.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HardwareServiceImpl implements HardwareService{

    private final HardwareRepository hardwareRepository;
    private final ReviewRepository reviewRepository;

    public HardwareServiceImpl(HardwareRepository hardwareRepository, ReviewRepository reviewRepository) {
        this.hardwareRepository = hardwareRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<HardwareDTO> findAll() {
        return hardwareRepository.findAll().stream().map(HardwareDTO::new).collect(Collectors.toList());
    }

    @Override
    public Optional<HardwareDTO> findByCode(String code) {
        return hardwareRepository.findByCode(code).map(HardwareDTO::new);
    }

    @Override
    public Optional<HardwareDTO> save(HardwareCommand hardwareCommand) {
        return hardwareRepository
                .save(new Hardware(hardwareCommand))
                .map(HardwareDTO::new);
    }

    @Override
    public Optional<HardwareDTO> update(String code, HardwareUpdateCommand updatedHardwareCommand) {
        return hardwareRepository
                .update(code, new Hardware(updatedHardwareCommand))
                .map(hardware -> new HardwareDTO(code, hardware));
    }

    @Transactional
    @Override
    public void deleteByCode(String code) {
        reviewRepository.deleteAllByHardware_Code(code);
        reviewRepository.flush();
        hardwareRepository.deleteByCode(code);
    }
}
