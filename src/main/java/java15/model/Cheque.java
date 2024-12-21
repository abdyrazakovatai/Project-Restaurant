package java15.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cheques")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Cheque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal priceAverage;
    private LocalDate createdAt;

    @ManyToOne(cascade = CascadeType.ALL) // persist
    private Employee employee;

    @ManyToMany(mappedBy = "cheques", cascade = CascadeType.PERSIST)
    private List<MenuItem> menuItems;

}
