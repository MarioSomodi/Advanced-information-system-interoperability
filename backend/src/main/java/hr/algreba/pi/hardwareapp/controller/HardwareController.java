package hr.algreba.pi.hardwareapp.controller;

import hr.algreba.pi.hardwareapp.command.HardwareCommand;
import hr.algreba.pi.hardwareapp.command.HardwareUpdateCommand;
import hr.algreba.pi.hardwareapp.dto.HardwareDTO;
import hr.algreba.pi.hardwareapp.service.HardwareService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("hardware")
//@CrossOrigin(origins = "http://localhost:4200")
public class HardwareController {

    private final HardwareService hardwareService;

    public HardwareController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }

    @GetMapping
    public List<HardwareDTO> getAll() {
        return hardwareService.findAll();
    }

    @GetMapping("{code}")
    public HardwareDTO getByCode(@PathVariable final String code) {
        return hardwareService.findByCode(code)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hardware was not found by that code")
                );
    }

    //@Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public HardwareDTO save(@Valid @RequestBody final HardwareCommand command){
            return hardwareService.save(command)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Hardware with the same code already exists"));
    }

    //@Secured("ROLE_ADMIN")
    @PutMapping("{code}")
    public HardwareDTO update(@PathVariable String code, @Valid @RequestBody final HardwareUpdateCommand updatedHardwareCommand){
        return hardwareService.update(code, updatedHardwareCommand)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hardware was not found by that code")
                );
    }

    //@Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{code}")
    public void delete(@PathVariable String code){
        hardwareService.deleteByCode(code);
    }

}
