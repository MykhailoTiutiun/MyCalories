package com.mykhailotiutiun.mycalories.persistence.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "diet_table")
@Getter
@ToString
public class Diet {

    @Id
    private Long id;
    @OneToOne
    private User user;

    private Integer dailyCalories;
    private Integer todayCalories;
    private Integer dailyProteins;
    private Integer todayProteins;
    private Integer dailyCarbs;
    private Integer todayCarbs;
    private Integer dailyFats;
    private Integer todayFats;

    public Diet(){

    }

    public Diet(Long id, User user) {
        this.id = id;
        this.user = user;
    }

    public void setDailyParams(Integer dailyCalories, Integer dailyProteins, Integer dailyCarbs, Integer dailyFats){
        this.dailyCalories = dailyCalories;
        this.dailyProteins = dailyProteins;
        this.dailyCarbs = dailyCarbs;
        this.dailyFats = dailyFats;
    }
    public void setTodayParams(Integer dailyCalories, Integer dailyProteins, Integer dailyCarbs, Integer dailyFats){
        this.dailyCalories = dailyCalories;
        this.dailyProteins = dailyProteins;
        this.dailyCarbs = dailyCarbs;
        this.dailyFats = dailyFats;
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
