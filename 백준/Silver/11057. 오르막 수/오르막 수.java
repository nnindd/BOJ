import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static final int MOD = 10_007;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		long[][] dp = new long[N + 1][11];

		for (int i = 1; i <= N; i++) {// N
			if (i == 1) {
				for (int j = 1; j < 11; j++) {
					dp[i][j] = 1; // N=1 에서 길이는 전부 1
				}
			} else {// 뒷자리가 0인 경우는 1개만 있음
				for (int j = 1; j < 11; j++) { // 뒷자리 0~9
					dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % MOD;
				}
			}
		}

		long hap = 0;
		for (int i = 0; i < 11; i++) {
			hap = hap + dp[N][i];
		}
		System.out.println(hap % MOD);
	}

}
