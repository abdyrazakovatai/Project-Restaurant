package java15.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "stop_lists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class StopList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String reason ;
    private LocalDate date;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    private MenuItem menuItem;
}
