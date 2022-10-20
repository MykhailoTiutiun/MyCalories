package com.mykhailotiutiun.mycalories.persistence.models;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "diet_table")
@Getter
@ToString
public class Diet {

    @Id
    private Long id;
    @OneToOne(mappedBy = "diet")
    @Transient
    private User user;

    private Float dailyCalories = 0F;
    private Float todayCalories = 0F;
    private Float dailyProteins = 0F;
    private Float todayProteins = 0F;
    private Float dailyCarbs = 0F;
    private Float todayCarbs = 0F;
    private Float dailyFats = 0F;
    private Float todayFats = 0F;

    @OneToMany
    @ToString.Exclude
    private Set<Meal> meals = new LinkedHashSet<>();

    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }

    public Diet(){

    }

    public Diet(Long id, User user) {
        this.id = id;
        this.user = user;
    }

    public Diet(Long id, User user, Float dailyCalories, Float todayCalories, Float dailyProteins, Float todayProteins, Float dailyCarbs, Float todayCarbs, Float dailyFats, Float todayFats) {
        this.id = id;
        this.user = user;
        this.dailyCalories = dailyCalories;
        this.todayCalories = todayCalories;
        this.dailyProteins = dailyProteins;
        this.todayProteins = todayProteins;
        this.dailyCarbs = dailyCarbs;
        this.todayCarbs = todayCarbs;
        this.dailyFats = dailyFats;
        this.todayFats = todayFats;
    }


    public void setDailyParams(Float dailyCalories, Float dailyProteins, Float dailyCarbs, Float dailyFats){
        this.dailyCalories = dailyCalories;
        this.dailyProteins = dailyProteins;
        this.dailyCarbs = dailyCarbs;
        this.dailyFats = dailyFats;
    }
    public void setTodayParams(Float todayCalories, Float todayProteins, Float todayCarbs, Float todayFats){
        this.todayCalories = todayCalories;
        this.todayProteins = todayProteins;
        this.todayCarbs = todayCarbs;
        this.todayFats = todayFats;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Diet diet = (Diet) o;
        return id != null && Objects.equals(id, diet.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
