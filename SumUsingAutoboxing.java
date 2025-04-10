import java.util.ArrayList;
import java.util.Scanner;

public class SumUsingAutoboxing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        System.out.print("Enter numbers separated by spaces: ");
        String input = scanner.nextLine();
        String[] tokens = input.split(" ");

        // Autoboxing: converting int to Integer automatically
        for (String token : tokens) {
            int num = Integer.parseInt(token); // parsing string to int
            numbers.add(num); // autoboxing happens here (int to Integer)
        }

        int sum = 0;
        for (Integer number : numbers) {
            sum += number; // unboxing happens here (Integer to int)
        }

        System.out.println("Sum of numbers: " + sum);
    }
}
