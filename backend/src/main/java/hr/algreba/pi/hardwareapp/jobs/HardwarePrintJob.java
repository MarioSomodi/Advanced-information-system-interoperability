package hr.algreba.pi.hardwareapp.jobs;

import hr.algreba.pi.hardwareapp.domain.Hardware;
import hr.algreba.pi.hardwareapp.repository.HardwareRepository;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Set;

public class HardwarePrintJob extends QuartzJobBean {

    private final Logger log = LoggerFactory.getLogger(HardwarePrintJob.class);

    private final HardwareRepository hardwareRepository;

    public HardwarePrintJob(HardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        final Set<Hardware> hardwareSet = hardwareRepository.findAll();

        if(hardwareSet.stream().anyMatch(this::isHardwareAvailable)){
            log.info("Ovo su trenutno dostupni hardveri");
            log.info("------------------------------");
            hardwareSet.stream()
                    .filter(this::isHardwareAvailable)
                    .forEach(
                            hardware -> log.info(hardware.toString())
                    );
            log.info("------------------------------");
        } else {
            log.info("Trenutno nema dostupnih hardvera");
        }

    }

    private boolean isHardwareAvailable(Hardware hardware) {
        return hardware.getStock() > 0;
    }
}
