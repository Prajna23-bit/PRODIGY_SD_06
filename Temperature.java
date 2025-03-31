import java.util.Scanner;

public class Temperature {

    public static void main(String[] args) {
        // Create a Scanner object to read input from the user
        Scanner scanner = new Scanner(System.in);

        // Get temperature input from the user
        System.out.print("Enter the temperature value: ");
        double temperature = scanner.nextDouble();

        // Get unit input from the user
        System.out.print("Enter the unit of the temperature (C for Celsius, F for Fahrenheit, K for Kelvin): ");
        char unit = scanner.next().toUpperCase().charAt(0);

        // Perform the conversions based on the input unit
        if (unit == 'C') {
            // Convert from Celsius to Fahrenheit and Kelvin
            double fahrenheit = (temperature * 9 / 5) + 32;
            double kelvin = temperature + 273.15;
            System.out.printf("%.2f°C is equal to %.2f°F and %.2fK.\n", temperature, fahrenheit, kelvin);
        } else if (unit == 'F') {
            // Convert from Fahrenheit to Celsius and Kelvin
            double celsius = (temperature - 32) * 5 / 9;
            double kelvin = celsius + 273.15;
            System.out.printf("%.2f°F is equal to %.2f°C and %.2fK.\n", temperature, celsius, kelvin);
        } else if (unit == 'K') {
            // Convert from Kelvin to Celsius and Fahrenheit
            double celsius = temperature - 273.15;
            double fahrenheit = (celsius * 9 / 5) + 32;
            System.out.printf("%.2fK is equal to %.2f°C and %.2f°F.\n", temperature, celsius, fahrenheit);
        } else {
            System.out.println("Invalid unit. Please enter C, F, or K.");
        }

        // Close the scanner
        scanner.close();
    }
}
