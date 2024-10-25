# Weather Forecast Service App

This is a Spring Boot application that provides current weather data and weather forecasts by consuming the OpenWeatherMap API.

## Features

- **Get Current Weather**: Fetches current weather data for a given city.
- **Get Weather Forecast**: Fetches a weather forecast for the next few days for a given city.

## Endpoints

The application exposes two main endpoints:

1. `/weather?city={cityName}`
    - Fetches the current weather for the given city.
    - **Example**: `/weather?city=Thessaloniki`
    - **Response**:
      ```json
      "Current Weather: City: Thessaloniki, Temperature: 22.5°C, Weather: Clear"
      ```

2. `/forecast?city={cityName}`
    - Fetches the weather forecast for the next few days for the given city.
    - **Example**: `/forecast?city=Thessaloniki`
    - **Response**: (Shows forecast for 2 or more upcoming days)
      ```json
      "Forecast: City: Thessaloniki, Day 1: 21.5°C, Clear Sky, Day 2: 20.2°C, Clouds"
      ```

## How It Works

- The application uses `RestTemplate` to make GET requests to the OpenWeatherMap API.
- JSON responses are parsed using `Jackson`'s `ObjectMapper` to extract and display relevant weather information.
- The API key is securely stored in `application.properties` and injected into the service using Spring's `@Value` annotation.

## Setup Instructions

1. Clone the repository:

    ```bash
    git clone https://github.com/your-repo/weather-app.git
    ```

2. Open the project in your preferred IDE (e.g., IntelliJ, Eclipse, or VS Code).

3. **Add your OpenWeatherMap API Key**:
    - Add your API key to the `application.properties` file in `src/main/resources/`:

      ```properties
      openweathermap.api.key=YOUR_API_KEY_HERE
      ```

4. Build and run the project:

    ```bash
    mvn spring-boot:run
    ```

5. Access the application by calling the REST endpoints via Postman or your browser:

    - For current weather: `http://localhost:8080/weather?city=Thessaloniki`
    - For weather forecast: `http://localhost:8080/forecast?city=Thessaloniki`

## Dependencies

- Spring Boot
- Spring Web
- Jackson (for JSON parsing)
- OpenWeatherMap API
