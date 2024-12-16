import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ReadingTheFile {
    public static void readFile() {
        Scanner sc = new Scanner(System.in);
        String file = sc.nextLine();
        Scanner reader = null;
        try {
            Path filePath = Paths.get(file);
            reader = new Scanner(filePath, "UTF-8");
            while (reader.hasNextLine()) {
                System.out.println(reader.nextLine());
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
    public static void initliazeDatas(){

    }

    public static void main(String[] args) {
        readFile();
        initliazeDatas();
    }
}
