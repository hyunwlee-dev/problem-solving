import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int[] p;
    public static int[] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        p = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }

        memo = new int[n + 1];

        // 1. Top Down
        Arrays.fill(memo, -1);
        memo[0] = 0;
        System.out.println(topDown(n));

        // 2. Bottom Up
        Arrays.fill(memo, -1);
        memo[0] = 0;
        System.out.println(bottomUp(n));
    }

    public static int topDown(int n) {

        if (n == 1) {
            memo[n] = p[n];
            return memo[n];
        } else {
            if (memo[n] > 0) {
                return memo[n];
            }
            for (int i = 1; i <= n; i++) {
//                memo[n] = p[i] + topDown(n - i) < memo[n] ? memo[n] : p[i] + memo[n - i];
//  위와 동일       memo[n] = p[i] + topDown(n - i) < memo[n] ? memo[n] : p[i] + topDown(n - i);
                if (memo[n] == -1 || p[i] + topDown(n - i) < memo[n]) {
                    memo[n] = p[i] + topDown(n - i);
                }
            }
            return memo[n];
        }
    }

    public static int bottomUp(int n) {

        for (int i = 1; i <= n; i++) {
//            memo[i] = -1;
            for (int j = 1; j <= i; j++) {
                if (memo[i] == -1 || memo[i] > p[j] + memo[i - j]) {
                    memo[i] = p[j] + memo[i - j];
                }
            }
        }
        return memo[n];
    }
}
