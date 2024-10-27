package gr.krionas.WeatherApp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    // API key for OpenWeatherMap, injected from application.properties
    @Value("${openweathermap.api.key}")
    private String apiKey;

    // Base URL for OpenWeatherMap API
    private final String baseUrl = "https://api.openweathermap.org/data/2.5/";

    // RestTemplate to handle HTTP requests
    private RestTemplate restTemplate = new RestTemplate();


    // Fetches current weather information for a given city.
    public String getWeather(String city) throws JsonProcessingException {
        String url = baseUrl + "weather?q=" + city + "&units=metric&appid=" + apiKey;

        // Send a GET request to the API and get the response as a String
        String response = restTemplate.getForObject(url, String.class);

        // Parse the JSON response
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response);

        // Extract relevant information from the JSON response
        String cityName = root.path("name").asText();
        double temperature = root.path("main").path("temp").asDouble();
        String weatherDescription = root.path("weather").get(0).path("description").asText();

        return String.format("Current Weather: City: %s, Temperature: %.2f°C, Weather: %s", cityName, temperature, weatherDescription);

    }

    //Fetches a 4-period weather forecast for a given city.
    public String getForecast(String city) throws JsonProcessingException {
        String url = baseUrl + "forecast?q=" + city + "&cnt=4&units=metric&appid=" + apiKey;
        String response = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response);
        String cityName = root.path("city").path("name").asText();
        List<String> responses = new ArrayList<>();
        for(int i=0; i<4; i++){

            double temperature = root.path("list").get(i).path("main").path("temp").asDouble();
            String time = root.path("list").get(i).path("dt_txt").asText();
            String description = root.path("list").get(i).path("weather").get(0).path("description").asText();
            responses.add(String.format("Forecast for %s at %s: %.2f°C Weather: %s", cityName, time, temperature, description));
        }
        return String.join("<br>", responses);


    }
}
