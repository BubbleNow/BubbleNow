package pl.bubblenow.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import java.util.List;

@Entity
@Table(name = "kind")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "bubbleTeas"})
public class Kind {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "Name is mandatory")
    @Size(min = 2, max = 45)
    private String name;
    private String file_path;
    private String image;

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

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getImage() {
        return "/uploads/" + file_path;
    }

}
