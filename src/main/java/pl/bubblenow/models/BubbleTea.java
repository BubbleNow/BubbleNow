package pl.bubblenow.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "bubble_tea")
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


    public BubbleTea(int id, Kind kind, Size size, Milk milk, Syrup syrup, Addition addition) {
        this.id = id;
        this.kind = kind;
        this.size = size;
        this.milk = milk;
        this.syrup = syrup;
        this.addition = addition;
    }

    public BubbleTea() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Syrup getSyrup() {
        return syrup;
    }

    public void setSyrup(Syrup syrup) {
        this.syrup = syrup;
    }

    public Addition getAddition() {
        return addition;
    }

    public void setAddition(Addition addition) {
        this.addition = addition;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Milk getMilk() {
        return milk;
    }

    public void setMilk(Milk milk) {
        this.milk = milk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Kind getKind() {
        return kind;
    }

    public void setKind(Kind kind) {
        this.kind = kind;
    }
}
