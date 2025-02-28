package club.code2create;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import club.code2create.AxisFlat;
import club.code2create.Hello;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose a sample to run (1: Hello, 2: AxisFlat, x: Exit): ");
            String choice = scanner.next();

            if (choice.equals("x")) {
                break;
            }

            switch (choice) {
                case "1":
                    Hello.run();
                    break;
                case "2":
                    System.out.println("Choose an AxisFlat operation (1: drawXYZAxis, 2: clearXYZAxis, 3: resetMinecraftWorld): ");
                    String axisChoice = scanner.next(); // axisChoiceを宣言し、ユーザー入力を取得
                    try {
                        switch (axisChoice) {
                            case "1":
                                AxisFlat.drawXYZAxis();
                                break;
                            case "2":
                                AxisFlat.clearXYZAxis();
                                break;
                            case "3":
                                AxisFlat.resetMinecraftWorld();
                                break;
                            default:
                                System.out.println("Invalid choice");
                        }
                    } catch (Exception e) {
                        LOGGER.log(Level.SEVERE, "An error occurred", e);
                    }
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        scanner.close();
    }
}
