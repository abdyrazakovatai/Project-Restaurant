package java15.model;

import jakarta.persistence.*;
import java15.enums.RestType;
import lombok.*;

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
    private int numberOfEmployees;
    private int service;

    @ToString.Exclude
    @OneToMany(mappedBy = "restaurant")
    private List<Employee> employees;

    @OneToMany(mappedBy = "restaurant",fetch = FetchType.EAGER)
    private List<MenuItem> menuItems;
}
