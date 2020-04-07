package com.sample.recipe_spring.models;

import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Data
// Must exclude Lombok version of hashcode on relationships or will create circular reference
@EqualsAndHashCode(exclude = "recipes")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER)
    private Set<Recipe> recipes;

}
