package com.mykhailotiutiun.mycalories.persistence.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user_table")
@Getter
@Setter
@ToString
public class User implements UserDetails {

    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @OneToOne
    private Details details;

    @OneToOne
    @ToString.Exclude
    private Diet diet;

    @ManyToMany
    @Transient
    @ToString.Exclude
    private Set<Dish> favoriteDishes = new LinkedHashSet<>();

    @OneToMany
    @Transient
    @ToString.Exclude
    private Set<Dish> ownedDishes = new LinkedHashSet<>();


    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(Long id, String name, String email, String password, Set<Role> roles, Details details, Diet diet, Set<Dish> favoriteDishes, Set<Dish> ownedDishes) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.details = details;
        this.diet = diet;
        this.favoriteDishes = favoriteDishes;
        this.ownedDishes = ownedDishes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
