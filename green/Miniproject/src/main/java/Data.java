public class Data {
    public static void main(String[] args) {

        WeatherData weatherData= new WeatherData();
        CurrentWeatherDisplay currentWeatherDisplay1= new CurrentWeatherDisplay();
        weatherData.registerObserver(currentWeatherDisplay1);

        weatherData.measurementChanged();
        System.out.println(weatherData);
        currentWeatherDisplay1.display();



    }
}