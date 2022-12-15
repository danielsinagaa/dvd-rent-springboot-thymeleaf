package com.rent.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "DVD")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DVD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String name;

    @Column(name = "new_film")
    private Boolean newFilm;

    @Column(name = "is_rented")
    private Boolean isRented;
}
