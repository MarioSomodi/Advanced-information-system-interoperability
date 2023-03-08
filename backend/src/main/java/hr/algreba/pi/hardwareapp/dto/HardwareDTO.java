package hr.algreba.pi.hardwareapp.dto;

import hr.algreba.pi.hardwareapp.domain.Hardware;

import java.math.BigDecimal;

public class HardwareDTO {
    private final String name;
    private final BigDecimal price;
    private final String code;

    public HardwareDTO(Hardware hardware) {
        this.name = hardware.getName();
        this.price = hardware.getPrice();
        this.code = hardware.getCode();
    }

    public HardwareDTO(String code, Hardware hardware) {
        this.name = hardware.getName();
        this.price = hardware.getPrice();
        this.code = code;
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
                ", code='" + code + '\'' +
                '}';
    }
}
