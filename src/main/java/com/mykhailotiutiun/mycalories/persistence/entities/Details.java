package com.mykhailotiutiun.mycalories.persistence.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "user_details_table")
@Setter
@Getter
@ToString
public class Details {

    @Id
    private Long id;

    @OneToOne(mappedBy = "details")
    @Transient
    private User user;

    private Integer age;
    private Integer height;
    private Integer weight;
    private Integer fat;

    public Details() {
    }

    public Details(Long id, User user) {
        this.id = id;
        this.user = user;
    }

    public Details(Long id, User user, Integer age, Integer height, Integer weight, Integer fat) {
        this.id = id;
        this.user = user;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.fat = fat;
    }

    public void setParams(Integer age, Integer height, Integer weight, Integer fat){
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.fat = fat;
    }
}
