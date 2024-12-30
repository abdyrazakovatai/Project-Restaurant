package java15.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "subcategoryies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ToString.Exclude
    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "subcategory",orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<MenuItem> menuItems;
}
