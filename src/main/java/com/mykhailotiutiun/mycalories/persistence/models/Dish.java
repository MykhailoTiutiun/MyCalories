package com.mykhailotiutiun.mycalories.persistence.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "dish_table")
@Getter
@Setter
@ToString
public class Dish {

    @Id
    private Long id;

    private String name;
    private String discription;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] image;
    private Float calories;
    private Float proteins;
    private Float carbs;
    private Float fats;

    private Integer views;
    private Integer recentViews;
    private LocalDate lastViewsUpdateDate;

    @ManyToMany
    @ToString.Exclude
    private Set<User> favoriteUsers = new LinkedHashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User ownedUser;

    public Dish() {
    }


    public Dish(Long id, String name, String discription, Float calories, Float proteins, Float carbs, Float fats, Integer views, Integer recentViews, LocalDate lastViewsUpdateDate, byte[] image, Set<User> favoriteUsers) {
        this.id = id;
        this.name = name;
        this.discription = discription;
        this.image = image;
        this.calories = calories;
        this.proteins = proteins;
        this.carbs = carbs;
        this.fats = fats;
        this.views = views;
        this.recentViews = recentViews;
        this.lastViewsUpdateDate = lastViewsUpdateDate;
        this.favoriteUsers = favoriteUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Dish dish = (Dish) o;
        return id != null && Objects.equals(id, dish.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
