package pl.bubblenow.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "syrup")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "bubbleTeas"})
public class Syrup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotNull(message = "Name is mandatory")
    @jakarta.validation.constraints.Size(min = 2, max = 45)
    private String name;

    @JsonIgnore
    private String filePath;

    @Column(nullable = false)
    @NotNull(message = "Color is mandatory")
    private String color;

    @OneToMany(mappedBy = "syrup", cascade = CascadeType.ALL)
    private List<BubbleTea> bubbleTeas;

    public String getImage() {
        return "/uploads/" + filePath;
    }
}
