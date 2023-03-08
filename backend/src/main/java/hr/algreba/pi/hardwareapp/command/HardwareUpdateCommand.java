package hr.algreba.pi.hardwareapp.command;

import hr.algreba.pi.hardwareapp.domain.Type;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class HardwareUpdateCommand {

    @NotBlank(message = "Name must not be empty")
    @Size(max = 200, message = "Name can't have more than 200 characters")
    private String name;

    @NotNull(message = "Type must be set to CPU, GPU, MBO, RAM, STORAGE or OTHER")
    private Type type;

    @PositiveOrZero(message = "Stock must be positive or zero")
    @Max(value = 10000, message = "Stock can't be above 10000")
    private long stock;

    @PositiveOrZero(message = "Price must be positive or zero")
    @Digits(integer=5, fraction=2, message = "Price can't be higher than 99999")
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public long getStock() {
        return stock;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
