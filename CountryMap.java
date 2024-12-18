public class CountryMap {
    private int cityCount;         
    private City[] cities;          
    public CountryMap(int cityCount) {
        this.cityCount = cityCount;
        cities = new City[cityCount];
    }
}