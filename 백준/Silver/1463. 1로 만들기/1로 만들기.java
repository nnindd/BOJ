import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] dp = new int[N + 1];
		dp[0] = 0;
		dp[1] = 0;

		// 1로 뺌
		// 2 or 3으로 나뉘면 나누고 최솟값 출력
		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i - 1] + 1; // 1을 뺀 횟수
			if (i % 2 == 0)	dp[i] = Math.min(dp[i], dp[i / 2] + 1);
			if (i % 3 == 0)	dp[i] = Math.min(dp[i], dp[i / 3] + 1);
		}

		System.out.println(dp[N]);
	}
}
