import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[][] dp;
        while (T-- > 0){
            int N = Integer.parseInt(br.readLine());
            dp = new int[2][N+2];
            for (int i = 0; i < 2; i++) {
                StringTokenizer tokens = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    dp[i][j] = Integer.parseInt(tokens.nextToken());
                }
            }//end input
            dp[0][2] += dp[1][1];
            dp[1][2] += dp[0][1];

            for (int i = 3; i <= N; i++) {
                dp[0][i] += Math.max(dp[1][i-1], dp[1][i-2]);
                dp[1][i] += Math.max(dp[0][i-1], dp[0][i-2]);
            }

            System.out.println(Math.max(dp[0][N], dp[1][N]));

        }//end test case
    }
}