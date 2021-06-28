package scanners;

import java.util.Scanner;

public class Scanners {
   private  static  Scanner scanner=new Scanner(System.in);

    public static long positiveDouble() {
        long number;
        do {
            while (!scanner.hasNextDouble()) {
                System.out.println("That's not a number!");
                scanner.next();
            }
            number = scanner.nextLong();
        } while (number <= 0);
        return number;
    }

    public static int intScanner(){
        int number;
        do {
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a number!");
                scanner.next();
            }
            number = scanner.nextInt();
        } while (number <= 0);
        return number;
    }

    public static String StringScanner(){
        while (!scanner.hasNext()){
            scanner.next();
        }
        return scanner.next();
    }
}
