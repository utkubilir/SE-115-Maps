public class WayFinder {
    private int[] shortestTime;
    private boolean[] visited;
    private int[] previous;
    
    public void findFastestPath(CountryMap map, int startCity, int endCity) {
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

        printResult(map, startCity, endCity, shortestTime[endCity]);//print the result function
    }
    private void printResult(CountryMap map, int start, int end, int totalTime) {
        if (totalTime == Integer.MAX_VALUE) {
            System.out.println("No path exists.");
        } else {
            String path = buildPath(map, start, end);
            System.out.println("Fastest Way: " + path);
            System.out.println("Total Time: " + totalTime + " min");
        }
    }
}
