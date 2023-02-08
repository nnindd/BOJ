import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static final int MOD = 10007;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N + 2];
		dp[1] = 1 % MOD;
		dp[2] = 3 % MOD;
		
		for(int i=3; i<=N; i++) {
			dp[i] = (dp[i-2]*2 + dp[i-1]) % MOD; 
		}

		System.out.println(dp[N] % MOD);
	}
}
