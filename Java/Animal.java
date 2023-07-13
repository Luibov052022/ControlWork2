import java.time.LocalDate;
import java.util.List;

public abstract class Animal {

  protected int ID = 0;
  protected String name;
  protected LocalDate birthday;
  protected List<String> commands;
  protected static int count = 1;

  public Animal(String name, LocalDate birthday, List<String> commands) {
    this.name = name;
    this.birthday = birthday;
    this.commands = commands;
    ID=count;
    count++;
  }

  public String getName() {
    return name;
  }

  public LocalDate getbirthday() {
    return birthday;
  }

  public List<String> getcommands() {
    return commands;
  }

  public void addcommand(String name) {
    getcommands().add(name);
  }

  public String getInfo() {
    return (this.getName() + " ID: " + ID + " Birthday: " + birthday + this.getcommands());
  }
}
