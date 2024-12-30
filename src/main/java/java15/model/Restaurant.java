package java15.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import java15.enums.RestType;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    @Enumerated(EnumType.STRING)
    private RestType restType;
    @Max(value = 15)
    private int numberOfEmployees;
    private BigDecimal service;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "restaurant")
    private List<Employee> employees;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "restaurant", fetch = FetchType.EAGER)
    private List<MenuItem> menuItems;
}
