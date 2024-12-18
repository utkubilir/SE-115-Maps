public class CountryMap {
    private int cityCount;         
    private City[] cities;
    private int[][] timeMatrix;           
    public CountryMap(int cityCount) {
        this.cityCount = cityCount;
        cities = new City[cityCount];
        timeMatrix = new int[cityCount][cityCount];

        
        for (int i = 0; i < cityCount; i++) {
            for (int j = 0; j < cityCount; j++) {
                if (i == j) {
                    timeMatrix[i][j] = 0; // Kendisine mesafe 0
                } else {
                    timeMatrix[i][j] = Integer.MAX_VALUE; // Sonsuz
                }
            }
        }
    }
    }