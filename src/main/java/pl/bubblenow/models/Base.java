package pl.bubblenow.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "base")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "bubbleTeas"})
public class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private BigDecimal price;

    @OneToMany(mappedBy = "base", cascade = CascadeType.ALL)
    private List<BubbleTea> bubbleTeas;

    public Base() {
    }


    public List<BubbleTea> getBubbleTeas() {
        return bubbleTeas;
    }

    public void setBubbleTeas(List<BubbleTea> bubbleTeas) {
        this.bubbleTeas = bubbleTeas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}