import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ReadingTheFile {
    public static int cityCount;
    public static String[] cityNames;
    public static String[][] routes;
    public static String startCity, endCity;

    public static void readFile() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the absolute path of the file:");
        String file = sc.nextLine();
        Scanner reader = null;

        try {
            Path filePath = Paths.get(file);///Users/utkubilir/Desktop/map1.txt
            reader = new Scanner(filePath);

            if (reader.hasNextInt()) {
                cityCount = reader.nextInt();
                reader.nextLine(); // Sayıyı okuduktan sonra satırı geç
            } else {
                throw new IOException("Invalid file format: Missing city count");
            }

            // Şehir isimlerini oku
            if (reader.hasNextLine()) {
                cityNames = reader.nextLine().split("\\s+");
                if (cityNames.length != cityCount) {
                    throw new IOException("City count does not match the number of city names");
                }
            } else {
                throw new IOException("Invalid file format: Missing city names");
            }

            // Rota sayısını oku
            int routeCount = 0;
            if (reader.hasNextInt()) {
                routeCount = reader.nextInt();
                reader.nextLine(); // Sayıyı okuduktan sonra satırı geç
            } else {
                throw new IOException("Invalid file format: Missing route count");
            }

            // Rotaları oku
            routes = new String[routeCount][3];
            for (int i = 0; i < routeCount; i++) {
                if (reader.hasNextLine()) {
                    String[] route = reader.nextLine().split("\\s+");
                    if (route.length != 3) {
                        throw new IOException("Invalid route format at line " + (i + 3 + cityCount));
                    }
                    routes[i] = route;
                } else {
                    throw new IOException("Invalid file format: Missing routes");
                }
            }

            // Başlangıç ve bitiş şehirlerini oku
            if (reader.hasNextLine()) {
                String[] startEnd = reader.nextLine().split("\\s+");
                if (startEnd.length != 2) {
                    throw new IOException("Invalid start-end city format");
                }
                startCity = startEnd[0];
                endCity = startEnd[1];
            } else {
                throw new IOException("Invalid file format: Missing start and end cities");
            }

            System.out.println("File read is successful!");
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    public static void initializeDatas() {
        System.out.println("City Count: " + cityCount);
        System.out.print("City Names: ");
        for (String city : cityNames) {
            System.out.print(city + " ");
        }
        System.out.println();
        System.out.println("Routes:");
        for (String[] route : routes) {
            System.out.println(route[0] + " -> " + route[1] + " : " + route[2] + " min");
        }
        System.out.println("Start City: " + startCity);
        System.out.println("End City: " + endCity);
    }

    public static void main(String[] args) {
        readFile();
        initializeDatas();
    }
}
