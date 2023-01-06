package pl.bubblenow.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "size")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "bubbleTeas"})
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private BigDecimal price;
    @OneToMany(mappedBy = "size", cascade = CascadeType.ALL)
    private List<BubbleTea> bubbleTeas;

    public Size() {
    }

    public Size(int id, String name, BigDecimal price, List<BubbleTea> bubbleTeas) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.bubbleTeas = bubbleTeas;
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
