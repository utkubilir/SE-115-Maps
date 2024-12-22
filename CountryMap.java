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
                    timeMatrix[i][j] = 0; 
                } else {
                    timeMatrix[i][j] = Integer.MAX_VALUE; 
                }
            }
        }
    }

    public void setCity(int index, String name) {
        cities[index] = new City(name);
    }

    public int findCityIndex(String name) {
        for (int i = 0; i < cityCount; i++) {
            if (cities[i].getName().equals(name)) {
                return i;
            }
        }
        return -1; 
    }

    public void addRoute(String city1, String city2, int time) {
        int index1 = findCityIndex(city1);
        int index2 = findCityIndex(city2);

        if (index1 == -1 || index2 == -1) {
            System.err.println("Error: Invalid city name(s) - " + city1 + " or " + city2);
        } else {
            timeMatrix[index1][index2] = time;
            timeMatrix[index2][index1] = time; 
        }
    }

    public int[][] getTimeMatrix() {
        return timeMatrix;
    }

    public City[] getCities() {
        return cities;
    }

    public int getCityCount() {
        return cityCount;
    }
}
