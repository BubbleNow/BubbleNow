package pl.bubblenow.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bubble_tea")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BubbleTea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kind_id")
    private Kind kind;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;

    @ManyToOne
    @JoinColumn(name = "milk_id")
    private Milk milk;

    @ManyToOne
    @JoinColumn(name = "syrup_id")
    private Syrup syrup;

    @ManyToOne
    @JoinColumn(name = "addition_id")
    private Addition addition;

    @OneToOne(mappedBy = "bubbleTea")
    @JsonBackReference
    private Order order;

}


