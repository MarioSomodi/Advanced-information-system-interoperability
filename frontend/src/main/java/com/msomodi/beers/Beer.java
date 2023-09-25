package com.msomodi.beers;

import java.math.BigDecimal;
import java.util.Objects;

public class Beer {
    private int id;
    private String name;

    private String description;
    private Double alcoholPercentage;
    public Beer() {
    }

    public Beer(int id, String name, String description, double alcoholPercentage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.alcoholPercentage = alcoholPercentage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }
    public void setAlcoholPercentage(double alcoholPercentage) {
        this.alcoholPercentage = alcoholPercentage ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beer beer = (Beer) o;
        return id == beer.id && Double.compare(beer.alcoholPercentage, alcoholPercentage) == 0 && Objects.equals(name, beer.name) && Objects.equals(description, beer.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, alcoholPercentage);
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", alcoholPercentage=" + alcoholPercentage +
                '}';
    }
}
