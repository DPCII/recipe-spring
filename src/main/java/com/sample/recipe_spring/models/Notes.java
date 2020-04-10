package com.sample.recipe_spring.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
// Must exclude Lombok version of hashcode on relationships or will create circular reference
@EqualsAndHashCode(exclude = "recipe")
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String notes;

    @Override
    public String toString() {
        return notes;
    }
}
