package hr.algreba.pi.hardwareapp.repository;

import hr.algreba.pi.hardwareapp.domain.Hardware;
import hr.algreba.pi.hardwareapp.domain.Type;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class MockHardwareRepository implements HardwareRepository{

    private final Set<Hardware> MOCKED_HARDWARE = Stream.of(
            new Hardware(1L, "Asus TUF RTX 3080", Type.GPU, "1234561", 0, BigDecimal.valueOf(1599.00)),
            new Hardware(2L, "EVGA XC3 RTX 3070 Ti", Type.GPU, "1234562", 0, BigDecimal.valueOf(1299.00)),
            new Hardware(3L, "AMD Ryzen 5950X", Type.CPU, "1234563", 0, BigDecimal.valueOf(899.00)),
            new Hardware(4L, "Samsung 980 PRO SSD 1TB", Type.STORAGE, "1234564", 0, BigDecimal.valueOf(299.00)),
            new Hardware(5L, "Kingston FURY Beast DDR5 32GB", Type.RAM, "1234565", 0, BigDecimal.valueOf(699.00))
    ).collect(Collectors.toSet());


    @Override
    public Set<Hardware> findAll() {
        return MOCKED_HARDWARE;
    }

    @Override
    public Optional<Hardware> findByCode(String code) {
        return MOCKED_HARDWARE.stream().filter(hardware -> Objects.equals(hardware.getCode(), code)).findAny();
    }

    @Override
    public Optional<Hardware> save(Hardware hardware) {
        if(!MOCKED_HARDWARE.contains(hardware)) {
            MOCKED_HARDWARE.add(hardware);
            return Optional.of(hardware);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Hardware> update(String code, Hardware updatedHardware) {
        boolean exists = MOCKED_HARDWARE.removeIf(
                hardware -> Objects.equals(hardware.getCode(), code) && Objects.equals(hardware.getCode(), updatedHardware.getCode())
        );

        if(exists) {
            MOCKED_HARDWARE.add(updatedHardware);
            return Optional.of(updatedHardware);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteByCode(String code) {
        MOCKED_HARDWARE.removeIf(hardware -> Objects.equals(hardware.getCode(), code));
    }
}
