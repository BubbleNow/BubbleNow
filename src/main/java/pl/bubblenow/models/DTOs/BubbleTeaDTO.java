package pl.bubblenow.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.bubblenow.models.*;
@Getter
@Setter
@AllArgsConstructor
public class BubbleTeaDTO {
  private  Addition addition;
  private  Syrup syrup;
  private  Milk milk;
  private  Size size;
  private  Kind kind;


}
