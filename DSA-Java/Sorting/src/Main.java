import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int a = input.nextInt();
        int b = input.nextInt();
        long c[] = new long[a];
        for (int k = 0; k < a; k++) {
            c[k] = input.nextLong();
        }
        long e = c[0];
        for (int j = 0; j < b; j++) {
            long w = input.nextLong();
            if (w < c[0]) {
                System.out.println(0);
            } else if (w >= c[a - 1]) {
                System.out.println(-1);
            } else {
                int sum = 0;
                while (e <= c[a - 1]) {
                    if (c[sum] <= w && c[sum + 1] > w) {
                        System.out.println(sum + 1);
                        break;
                    } else {
                        sum += 1;
                        e = c[sum];
                    }
                }
            }
        }

    }
}
