package hr.algreba.pi.hardwareapp.dto;

import hr.algreba.pi.hardwareapp.domain.Hardware;
import hr.algreba.pi.hardwareapp.domain.Type;

import java.math.BigDecimal;

public class HardwareDTO {
    private final String name;
    private final BigDecimal price;
    private final String code;
    private final Type type;
    private final Long stock;

    public HardwareDTO(Hardware hardware) {
        this.name = hardware.getName();
        this.price = hardware.getPrice();
        this.code = hardware.getCode();
        this.type = hardware.getType();
        this.stock = hardware.getStock();
    }

    public HardwareDTO(String code, Hardware hardware) {
        this.name = hardware.getName();
        this.price = hardware.getPrice();
        this.type = hardware.getType();
        this.stock = hardware.getStock();
        this.code = code;
    }

    public Type getType() {
        return type;
    }

    public Long getStock() {
        return stock;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "HardwareDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", type=" + type +
                ", code='" + code + '\'' +
                '}';
    }
}
