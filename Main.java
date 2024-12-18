import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Enter the absolute path of the input file:");
        String filePath = inputScanner.nextLine();

        try {
            Path path = Paths.get(filePath);
            Scanner fileScanner = new Scanner(path.toFile());
             int cityCount = Integer.parseInt(fileScanner.nextLine().trim());