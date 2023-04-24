import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static final int MOD = 1_000_000_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		long[][] dp = new long[N + 1][10];

		// 첫번째 계단 길이 설정
		for (int i = 1; i < 10; i++) { // 첫번째 계단 길이
			dp[1][i] = 1;
		} // 1 2 3 4 5 6 7 8 9

		// dp[i][0] -> 0의 앞에 1이 오는 경우
		// dp[i][1] -> 1의 앞이 0과 2가 오는 경우
		// ...
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 10; j++) {
				if (j == 0) // 0일땐
					dp[i][0] = dp[i - 1][1] % MOD;
				else if (j == 9)
					dp[i][9] = dp[i - 1][8] % MOD;
				else
					dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MOD;
			}
		}

		long hap = 0;
		for (int i = 0; i < 10; i++) {
			hap = hap + dp[N][i];
		}
		System.out.println(hap % MOD);
	}

}
