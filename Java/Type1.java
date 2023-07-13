import java.time.LocalDate;
import java.util.List;

public abstract class Type1 extends Animal {

  private String nametype;

  public Type1(
    String name,
    String nametype,
    LocalDate birthday,
    List<String> commands
  ) {
    super(name, birthday, commands);
    this.nametype = nametype;
  }

  
  public String getnametype1() {
    return nametype;
  }
}
