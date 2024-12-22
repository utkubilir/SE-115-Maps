import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Error: Please provide the input file path as a command-line argument.");
            return;
        }
        String filePath = args[0];
        try {
            Path path = Paths.get(filePath);
            Scanner fileScanner = new Scanner(path.toFile());

            int cityCount = Integer.parseInt(fileScanner.nextLine().trim());
            CountryMap map = new CountryMap(cityCount);

            String[] cityLabels = fileScanner.nextLine().trim().split("\\s+");
            for (int i = 0; i < cityCount; i++) {
                map.setCity(i, cityLabels[i]);
            }

            int routeCount = Integer.parseInt(fileScanner.nextLine().trim());
            for (int i = 0; i < routeCount; i++) {
                String[] route = fileScanner.nextLine().trim().split("\\s+");
                map.addRoute(route[0], route[1], Integer.parseInt(route[2]));
            }

            String[] cities = fileScanner.nextLine().trim().split("\\s+");
            int startCity = map.findCityIndex(cities[0]);
            int endCity = map.findCityIndex(cities[1]);

            WayFinder wayFinder = new WayFinder();
            wayFinder.findFastestPathAndWriteResults(map, startCity, endCity, "output.txt");

            fileScanner.close();
        } catch (IOException e) {
            System.err.println("Error: Unable to read file - " + e.getMessage());
        } finally {
            inputScanner.close();
        }
    }
    }



 