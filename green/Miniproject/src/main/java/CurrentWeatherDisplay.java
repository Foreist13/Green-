public class CurrentWeatherDisplay implements  Display,Observer {
    private  float temperature;
    private  float humidity;
    private float pressure;


    @Override
    public void display() {

        System.out.println("CURRENT WEATHER DATA");
        System.out.println("Current temp: " + temperature);
        System.out.println("Currnt hum: " + humidity);
        System.out.println("Currnt press: " + pressure);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature=temperature;
        this.humidity=humidity;
        this.pressure=pressure;
    }
}