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
            Scanner fileScanner = new Scanner(path.toFile(), "UTF-8");

            int lineNumber = 1;
            boolean hasErrors = false;

            int cityCount = 0;
            if (fileScanner.hasNextLine()) {
                String cityCountLine = fileScanner.nextLine().trim();
                lineNumber++;
                try {
                    cityCount = Integer.parseInt(cityCountLine);
                } catch (NumberFormatException e) {
                    System.err.println("Error Line: " + lineNumber + " Invalid city count.");
                    return;
                }
            } else {
                System.err.println("Error Line: " + lineNumber + " Missing city count.");
                return;
            }

            CountryMap map = new CountryMap(cityCount);

            if (fileScanner.hasNextLine()) {
                String[] cityLabels = fileScanner.nextLine().trim().split("\\s+");
                lineNumber++;
                if (cityLabels.length != cityCount) {
                    System.err.println("Error Line: " + lineNumber + " Number of city names does not match city count.");
                    hasErrors = true;
                } else {
                    for (int i = 0; i < cityCount; i++) {
                        map.setCity(i, cityLabels[i]);
                    }
                }
            } else {
                System.err.println("Error Line: " + lineNumber + " Missing city names.");
                return;
            }

            int routeCount = 0;
            if (fileScanner.hasNextLine()) {
                String routeCountLine = fileScanner.nextLine().trim();
                lineNumber++;
                try {
                    routeCount = Integer.parseInt(routeCountLine);
                } catch (NumberFormatException e) {
                    System.err.println("Error Line: " + lineNumber + " Invalid route count.");
                    return;
                }
            }

            for (int i = 0; i < routeCount; i++) {
                if (fileScanner.hasNextLine()) {
                    String[] route = fileScanner.nextLine().trim().split("\\s+");
                    lineNumber++;
                    if (route.length != 3) {
                        System.err.println("Error Line: " + lineNumber + " Invalid route format.");
                        hasErrors = true;
                        continue;
                    }

                    String city1 = route[0];
                    String city2 = route[1];
                    int time;
                    try {
                        time = Integer.parseInt(route[2]);
                        map.addRoute(city1, city2, time);
                    } catch (NumberFormatException e) {
                        System.err.println("Error Line: " + lineNumber + " Invalid time value.");
                        hasErrors = true;
                    }
                } else {
                    System.err.println("Error Line: " + lineNumber + " Missing route information.");
                    return;
                }
            }
            if (fileScanner.hasNextLine()) {
                String[] cities = fileScanner.nextLine().trim().split("\\s+");
                lineNumber++;
                if (cities.length != 2) {
                    System.err.println("Error Line: " + lineNumber + " Invalid start and end city format.");
                    hasErrors = true;
                } else {
                    int startCity = map.findCityIndex(cities[0]);
                    int endCity = map.findCityIndex(cities[1]);

                    if (!hasErrors) {
                        WayFinder wayFinder = new WayFinder();
                        wayFinder.findFastestPathAndWriteResults(map, startCity, endCity, "output.txt");
                        System.out.println("File read is successful!");
                    }
                }
            } else {
                System.err.println("Error Line: " + lineNumber + " Missing start and end cities.");
                return;
            }

            fileScanner.close();
        } catch (Exception e) {
            System.err.println("Error: Unable to read file - " + e.getMessage());
        }
    }
}
