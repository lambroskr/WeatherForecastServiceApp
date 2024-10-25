package gr.krionas.WeatherApp;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
    @Autowired
    WeatherService service;

    @GetMapping("/weather")
    public String getWeather(@RequestParam String city) throws JsonProcessingException {
        return service.getWeather(city);
    }

    @GetMapping("/forecast")
    public String getForecast(@RequestParam String city) throws JsonProcessingException {
        return service.getForecast(city);
    }
}
