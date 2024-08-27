package Task4;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CurrencyConverter {

    private static final String BASE_URL = "https://api.exchangerate-api.com/v4/latest/";
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("*****************Welcome to task 4 :My Currency Converter*****************");
        List<String> currencies = loadCurrencies();
        if (currencies.isEmpty()) {
            System.out.println("Failed to load currencies. Exiting.");
            return;
        }

        while (true) {
            String fromCurrency = getCurrencyInput("Enter the base currency (or 'exit' to quit): ", currencies);
            if (fromCurrency.equalsIgnoreCase("exit")) {
                break;
            }

            String toCurrency = getCurrencyInput("Enter the target currency: ", currencies);
            double amount = getAmountInput();

            try {
                double convertedAmount = convertCurrency(fromCurrency, toCurrency, amount);
                System.out.printf("%.2f %s = %.2f %s%n", amount, fromCurrency, convertedAmount, toCurrency);
            } catch (Exception e) {
                System.out.println("Error converting currency: " + e.getMessage());
            }

            System.out.println();
        }

        System.out.println("Thank you for using the My Currency Converter!");
        scanner.close();
    }

    private static List<String> loadCurrencies() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "USD"))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            org.json.JSONObject jsonResponse = new org.json.JSONObject(response.body());
            org.json.JSONObject rates = jsonResponse.getJSONObject("rates");

            List<String> currencies = new ArrayList<>(rates.keySet());
            Collections.sort(currencies);
            return currencies;
        } catch (Exception e) {
            System.out.println("Error loading currencies: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static String getCurrencyInput(String prompt, List<String> currencies) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().toUpperCase();
            if (currencies.contains(input) || input.equalsIgnoreCase("exit")) {
                return input;
            }
            System.out.println("Invalid currency. Please try again.");
        }
    }

    private static double getAmountInput() {
        while (true) {
            System.out.print("Enter the amount to convert: ");
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a number.");
            }
        }
    }

    private static double convertCurrency(String from, String to, double amount) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + from))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject jsonResponse = new JSONObject(response.body());
        JSONObject rates = jsonResponse.getJSONObject("rates");

        double rate = rates.getDouble(to);
        return amount * rate;
    }
}

