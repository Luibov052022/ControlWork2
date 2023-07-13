import java.io.IOException;

public class main {

  public static void main(String[] args) throws IOException {
    Controller.init();
    Controller.printMenu();
    while (true) {
      Controller.choice();
      Controller.printMenu();
    }
   
  }
}
