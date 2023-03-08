package hr.algreba.pi.hardwareapp.domain;

import hr.algreba.pi.hardwareapp.command.HardwareCommand;
import hr.algreba.pi.hardwareapp.command.HardwareUpdateCommand;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Hardware {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Type type;
    private String code;
    private long stock;
    private BigDecimal price;

    public Hardware() {
    }

    public Hardware(Long id, String name, Type type, String code, long stock, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.code = code;
        this.stock = stock;
        this.price = price;
    }

    public Hardware(HardwareCommand hardwareCommand) {
        this.name = hardwareCommand.getName();
        this.type = hardwareCommand.getType();
        this.code = hardwareCommand.getCode();
        this.stock = hardwareCommand.getStock();
        this.price = hardwareCommand.getPrice();
    }

    public Hardware(HardwareUpdateCommand hardwareCommand) {
        this.name = hardwareCommand.getName();
        this.type = hardwareCommand.getType();
        this.stock = hardwareCommand.getStock();
        this.price = hardwareCommand.getPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    public long getStock() {
        return stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hardware hardware = (Hardware) o;
        return stock == hardware.stock && Objects.equals(id, hardware.id) && Objects.equals(name, hardware.name) && type == hardware.type && Objects.equals(code, hardware.code) && Objects.equals(price, hardware.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, code, stock, price);
    }

    @Override
    public String toString() {
        return name + " - " + stock;
    }
}
