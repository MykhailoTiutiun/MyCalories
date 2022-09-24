package com.mykhailotiutiun.mycalories.persistence.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "meal_table")
@Getter
@Setter
@ToString
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String name;

    private Float calories;
    private Float proteins;
    private Float carbs;
    private Float fats;

    @ManyToOne(fetch = FetchType.LAZY)
    @Transient
    @ToString.Exclude
    private Diet diet;

    public Meal() {
    }

    public Meal(String name, Diet diet) {
        this.name = name;
        this.diet = diet;
        calories = 0F;
        proteins = 0F;
        carbs = 0F;
        fats = 0F;
    }

    public Meal(Long id, String name, Float calories, Float proteins, Float carbs, Float fats, Diet diet) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.proteins = proteins;
        this.carbs = carbs;
        this.fats = fats;
        this.diet = diet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Meal meal = (Meal) o;
        return id != null && Objects.equals(id, meal.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
