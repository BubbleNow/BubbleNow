package pl.bubblenow.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "`order`")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private int number;

    private BigDecimal price;

    private String status = "Nowe";

    @OneToOne
    @JoinColumn(name = "bubble_tea_id")
    @JsonManagedReference
    private BubbleTea bubbleTea;

    private Date date;


}
