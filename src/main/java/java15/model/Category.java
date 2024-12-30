package java15.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categoryes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Subcategory> subcategories;
}
