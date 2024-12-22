import java.io.FileOutputStream;
import java.io.IOException;

public class WayFinder {
    private int[] shortestTime;
    private boolean[] visited;
    private int[] previous;

    public void findFastestPathAndWriteResults(CountryMap map, int startCity, int endCity, String outputFilePath) {
        int cityCount = map.getCityCount();
        shortestTime = new int[cityCount];
        visited = new boolean[cityCount];
        previous = new int[cityCount];

        for (int i = 0; i < cityCount; i++) {
            shortestTime[i] = Integer.MAX_VALUE;
            previous[i] = -1;
        }
        shortestTime[startCity] = 0;

        int[][] timeMatrix = map.getTimeMatrix();

        for (int i = 0; i < cityCount; i++) {
            int minTime = Integer.MAX_VALUE;
            int currentCity = -1;

            for (int j = 0; j < cityCount; j++) {
                if (!visited[j] && shortestTime[j] < minTime) {
                    minTime = shortestTime[j];
                    currentCity = j;
                }
            }

            if (currentCity == -1) break;
            visited[currentCity] = true;

            for (int j = 0; j < cityCount; j++) {
                if (!visited[j] && timeMatrix[currentCity][j] != Integer.MAX_VALUE) {
                    int newTime = shortestTime[currentCity] + timeMatrix[currentCity][j];
                    if (newTime < shortestTime[j]) {
                        shortestTime[j] = newTime;
                        previous[j] = currentCity;
                    }
                }
            }
        }

        try (FileOutputStream fos = new FileOutputStream(outputFilePath)) {
            if (shortestTime[endCity] == Integer.MAX_VALUE) {
                String result = "No path exists.";
                System.out.println(result);
                fos.write(result.getBytes());
            } else {
                String path = buildPath(map, startCity, endCity);
                String result = "Fastest Way: " + path + "\nTotal Time: " + shortestTime[endCity] + " min";
                System.out.println(result);
                fos.write(result.getBytes());
            }
            System.out.println("Results saved to: " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private String buildPath(CountryMap map, int start, int end) {
        if (end == start) {
            return map.getCities()[start].getName();
        } else if (previous[end] == -1) {
            return "No path";
        } else {
            return buildPath(map, start, previous[end]) + " -> " + map.getCities()[end].getName();
        }
    }
}