import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.management.RuntimeErrorException;

public class Controller {

  public static List<Animal> animals;

  public static void init() {
    animals = new ArrayList<>();
    String type1 = "";
    String type2 = "";
    for (int i = 0; i < 2; i++) {
      switch (i) {
        case 0 -> type1 = "Pets";
        default -> type1 = "Pаск";
      }
      for (int j = 0; j < 3; j++) {
        if (i == 0) {
          switch (j) {
            case 1 -> type2 = "Cat";
            case 2 -> type2 = "Dog";
            default -> type2 = "Hamster";
          }
        } else {
          switch (j) {
            case 1 -> type2 = "Horse";
            case 2 -> type2 = "Donkey";
            default -> type2 = "Kamel";
          }
        }
        Random ran = new Random();
        String name = generateString(ran, "абсдефжклмнопр", 10);
        List<String> commands = new ArrayList<>();
        String c1 = generateString(ran, "абсдефжклмнопр", 5);
        String c2 = generateString(ran, "абсдефжклмнопр", 5);
        String c3 = generateString(ran, "абсдефжклмнопр", 5);
        commands.add(c1);
        commands.add(c2);
        commands.add(c3);
        Type2 animal = new Type2(
          name,
          type2,
          type1,
          LocalDate.of(2019, 2, 22),
          commands
        );
        animals.add(animal);
      }
    }
    printList(animals);
  }

  public static void printMenu() {
    System.out.println(" ");
    System.out.println("Меню");
    System.out.println(" ");
    System.out.println("1. Добавить  животное");
    System.out.println("2. Добавить  команду");
    System.out.println("3. Печать списка животных");
    System.out.println("4. Выход");
  }

  public static void choice() throws IOException {
    Scanner sc = new Scanner(System.in);
    System.out.println("Введите пункт меню: ");
    int number = sc.nextInt();

    switch (number) {
      case 1 -> addAnimal();
      case 2 -> addCommand();
      case 3 -> printList(animals);
      case 4 -> System.exit(0);
      default -> System.out.println("Неверный ввод!");
    }
  }

  public static void printList(List<Animal> animals2) {
    for (Animal i : animals2) {
      System.out.println(i.getInfo());
    }
  }

  public static String generateString(
    Random rng,
    String characters,
    int length
  ) {
    char[] text = new char[length];
    for (int i = 0; i < length; i++) {
      text[i] = characters.charAt(rng.nextInt(characters.length()));
    }
    return new String(text);
  }

  public static void addAnimal() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Введите имя: ");
    String name = sc.nextLine();
    System.out.println("Введите Тип2 животного: ");
    String type2 = sc.nextLine();
    System.out.println("Введите Тип1 животного (домашнне, вьючное): ");
    String type1 = sc.nextLine();

    System.out.println(
      "Введите дату рождения животного в формате ГГГГ-ММ-ДД: "
    );
    String strbirthday = sc.nextLine();
    LocalDate birthday = null;
    try {
      String[] words = strbirthday.split("-");
      birthday =
        LocalDate.of(
          Integer.parseInt(words[0]),
          Integer.parseInt(words[1]),
          Integer.parseInt(words[2])
        );
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new RuntimeException("проверьте формат ввода!");
    }

    System.out.println("Введите команды через запятую: ");
    String strcom = sc.nextLine();
    String[] strCommands = strcom.split(",");
    List<String> commands = new ArrayList<>();
    for (String i : strCommands) {
      commands.add(i);
    }

    if (!name.isEmpty() & !type1.isEmpty() & !type2.isEmpty()) {
      Type2 animal = new Type2(name, type2, type1, birthday, commands);
      animals.add(animal);
    } else {
      System.out.println("проверьте формат ввода! Животное не добавлено");
    }
  }

  public static void addCommand() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Введите ID животного: ");
    int ID = sc.nextInt();
    Scanner sc2 = new Scanner(System.in);
    System.out.println("Введите команду: ");
    String name = sc2.nextLine();
    if (!name.isEmpty()) {
      animals.get(ID - 1).addcommand(name);
    } else {
      System.out.println("Пустой ввод, команда не добавлена");
    }
  }
}
