
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int MOD = 1_000_000_009;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];

		StringTokenizer tokens = new StringTokenizer(br.readLine());
		int[] P = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			P[i] = Integer.parseInt(tokens.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= i; j++) {
				dp[i] = Math.max(dp[i], dp[i-j] + P[j]);
			}
		}

		System.out.println(dp[N]);
	}
}
