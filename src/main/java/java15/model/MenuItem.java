package java15.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "menultems")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private BigDecimal price;
    private String description;
    private boolean isVegetarian;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "menuItem")
    private StopList stoplist;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    @ManyToOne
//            (cascade = CascadeType.ALL)
    private Subcategory subcategory;

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Cheque> cheques;
}
