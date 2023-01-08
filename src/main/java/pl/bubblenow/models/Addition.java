package pl.bubblenow.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "addition")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "bubbleTeas"})
public class Addition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Name is mandatory")
    @Size(min = 2, max = 45)
    private String name;
    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Digits(integer = 6, fraction = 2)
    private BigDecimal price;

    @OneToMany(mappedBy = "addition", cascade = CascadeType.ALL)
    private List<BubbleTea> bubbleTeas;

    public Addition() {
    }

    public Addition(int id, String name, BigDecimal price, List<BubbleTea> bubbleTeas) {
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
