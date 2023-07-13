import java.time.LocalDate;
import java.util.List;

public class Type2 extends Type1 {

  private String nametype2;

  public Type2(
    String name,
    String nametype2,
    String nametype,
    LocalDate birthday,
    List<String> commands
  ) {
    super(name, nametype, birthday, commands);
    this.nametype2 = nametype2;
  }

  public String getnametype2() {
    return nametype2;
  }

  public String getInfo() {
    return (
      this.getName() +
      " ID: " +
      ID +
      " Birthday: " +
      birthday +
      " Вид 2: " +
      nametype2 +
      " Вид 1: " +
      this.getnametype1() +
      " Commands: " +
      this.getcommands()
    );
  }
}
