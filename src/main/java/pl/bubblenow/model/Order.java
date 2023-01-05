package pl.bubblenow.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int number;
    private BigDecimal price;

    @OneToOne
    @JoinColumn(name = "bubble_tea_id")
    private BubbleTea bubbleTea;
    private Date date;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BubbleTea getBubbleTea() {
        return bubbleTea;
    }

    public void setBubbleTea(BubbleTea bubbleTea) {
        this.bubbleTea = bubbleTea;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
