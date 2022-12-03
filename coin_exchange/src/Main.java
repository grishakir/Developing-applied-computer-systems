import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> denomination = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter amount of money:");
        int n = 0;
        int amount = 0;

        try {
            amount = in.nextInt();
            System.out.println("Enter number of coin denominations:");
            n = in.nextInt();
            System.out.println("Enter Coin denomination:");
            for (int i = 0; i < n; ++i) {
                int k = in.nextInt();
                denomination.add(k);
            }
        } catch (InputMismatchException e) {
            System.out.println("Incorrect input!");
            return;
        }

        Integer[] result = new Integer[n];

        Collections.sort(denomination, Collections.reverseOrder());
        Arrays.fill(result, 0);
        int status = exchange(amount, denomination, result, 0);

        if (status != 0) {
            System.out.println("Cannot exchange");
        } else {
            System.out.print(amount + " ->");
            for (int i = 0; i < n; ++i) {
                System.out.print(
                        " " + denomination.get(i).toString()
                        + "[" + result[i].toString() + "]");
            }
        }

    }

    private static int exchange(Integer amount, ArrayList<Integer> denomination, Integer[] result, int i) {
        if (denomination.size() - 1 < i && amount == 0) {
            return 0;
        } else if (denomination.size() - 1 < i && amount != 0) {
            return 1;
        } else {
            if (amount < denomination.get(i)) {
                return exchange(amount, denomination, result, i + 1);
            } else {
                amount -= denomination.get(i);
                result[i] += 1;
                return exchange(amount, denomination, result, i);
            }
        }
    }
}