import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class WeatherData implements Subject {
    private static final String API_KEY = "46a9238500611ede63723e1b94f2e18e";
    private static final String CITY = "Wroclaw,pl";
    private static final String URL = "https://api.openweathermap.org/data/2.5/weather?q=" + CITY + "&appid=" + API_KEY + "&units=metric";

    private float temperature;
    private float humidity;
    private float pressure;

    private List<Observer> observers = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        WeatherData weatherData = new WeatherData();
        weatherData.fetchWeatherData();
    }

    private void fetchWeatherData() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(URL)).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);
        JsonObject main = jsonObject.getAsJsonObject("main");
        temperature = main.get("temp").getAsFloat();
        pressure = main.get("pressure").getAsInt();
        humidity = main.get("humidity").getAsInt();

        notifyObserver();
    }
    @Override
    public void registerObserver(Observer o) {
        if (! observers.contains(o ))observers.add(o);

    }

    @Override
    public void removeObserver(Observer o) {

        if(observers.contains(o)) observers.remove(o);

    }

    @Override
    public void notifyObserver() {
        for(Observer o : observers)
            o.update(temperature,humidity,pressure);
    }

    public void measurementChanged(){

        temperature= (float )(15+(Math.random()-0.5)*20);
        humidity= (float)(100*Math.random());
        pressure=1000;
        notifyObserver();
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", observers=" + observers +
                '}';
    }
}