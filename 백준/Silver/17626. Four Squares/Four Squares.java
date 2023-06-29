import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        //1 >> 1        1
        //2 >> 1+1      2
        //3 >> 1+1+1    3
        //4 >> 2        1
        //5 >> 1+2      2
        //6 >> 1+1+2    3
        //7 >> 1+1+1+2  4
        //8 >> 2+2      2
        //9 >> 3        1

        //기본 dp[i] = dp[i-1] + 1
        //지금 수에서 제곱수를 뺀 값이 더 적을 때
        //ex) [4-2*2] -> 2*2 = 1, dp[0] = 0 + 1;

        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);

            }
        }
        System.out.println(dp[N]);
    }
}
