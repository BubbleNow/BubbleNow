package pl.bubblenow.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name = "kind")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "bubbleTeas"})
public class Kind {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "kind")
    private List<BubbleTea> bubbleTeas;

    public Kind(int id, String name, List<BubbleTea> bubbleTeas) {
        this.id = id;
        this.name = name;
        this.bubbleTeas = bubbleTeas;
    }

    public List<BubbleTea> getBubbleTeas() {
        return bubbleTeas;
    }

    public void setBubbleTeas(List<BubbleTea> bubbleTeas) {
        this.bubbleTeas = bubbleTeas;
    }

    public Kind() {

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

    @Override
    public String toString() {
        return "Kind{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}
