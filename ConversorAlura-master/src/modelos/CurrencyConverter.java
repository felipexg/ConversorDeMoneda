package modelos;

import APIs.ExchangeRateApiResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyConverter {

    private static final String API_KEY = "3b129534e47d9b801bc0c4a1"; // Reemplaza con tu clave de API
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";

    public ExchangeRateApiResponse getExchangeRates() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .build();
        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new Gson().fromJson(response.body(), ExchangeRateApiResponse.class);
    }

    public double convertCurrency(double amount, double fromRate, double toRate) {
        return (amount / fromRate) * toRate;
    }
}
