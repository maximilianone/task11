package input;

import java.util.Scanner;

public class MyScanner {
    private Scanner scanner;

    public MyScanner(){
        scanner = new Scanner(System.in);
    }

    public void closeScanner(){
        this.scanner.close();
    }

    public String readInput() {
        return this.scanner.nextLine();
    }
}
