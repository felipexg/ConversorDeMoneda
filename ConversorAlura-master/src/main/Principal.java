package main;

import APIs.ExchangeRateApiResponse;
import modelos.CurrencyConverter;

import java.util.Map;
import java.util.Scanner;




public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CurrencyConverter converter = new CurrencyConverter();

        System.out.println("Bienvenido al convertidor de tasas de cambio de Alura Latam, por favor escoja su moneda entre las siguientes opciones.");
        ExchangeRateApiResponse rates = converter.getExchangeRates();
        Map<String, Double> conversionRates = rates.getConversion_rates();

        String[] supportedCurrencies = {"ARS", "BOB", "BRL", "CLP", "COP", "USD"};

        System.out.println("Monedas disponibles:");
        for (int i = 0; i < supportedCurrencies.length; i++) {
            System.out.println((i + 1) + ". " + supportedCurrencies[i]);
        }

        System.out.print("Seleccione la moneda de origen (1-6): ");
        int fromIndex = scanner.nextInt() - 1;
        String fromCurrency = supportedCurrencies[fromIndex];

        System.out.print("Seleccione la moneda de destino (1-6): ");
        int toIndex = scanner.nextInt() - 1;
        String toCurrency = supportedCurrencies[toIndex];

        System.out.print("Ingrese el valor a convertir: ");
        double amount = scanner.nextDouble();

        double fromRate = conversionRates.get(fromCurrency);
        double toRate = conversionRates.get(toCurrency);
        double result = converter.convertCurrency(amount, fromRate, toRate);

        System.out.printf("%.2f %s son %.2f %s\n", amount, fromCurrency, result, toCurrency);
    }
}
