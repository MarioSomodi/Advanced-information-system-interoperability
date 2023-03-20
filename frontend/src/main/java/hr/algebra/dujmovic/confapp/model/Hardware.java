package hr.algebra.dujmovic.confapp.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Hardware {

    private Long id;
    private String name;

    private Type type;
    private String code;
    private long stock;
    private BigDecimal price;

    public Hardware() {
    }

    public Hardware(Long id, String name, Type type, String code, Long stock, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.code = code;
        this.stock = stock;
        this.price = price;
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

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    public Long getStock() {
        return stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
