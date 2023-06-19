import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N+2];
		int[] dp = new int[N+2];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine()); 
		}
		
		dp[1] = arr[1];
		dp[2] = dp[1] + arr[2];
		
		for(int i=3; i<=N; i++) {
			//지금 이전 그전전 마시는 경우 vs 지금 이전 마시는 경우
			dp[i] = Math.max(dp[i-3] + arr[i-1] + arr[i], dp[i-2] + arr[i]);
			//지금 마시는 경우 vs 이전 마시는 경우
			dp[i] = Math.max(dp[i], dp[i-1]);
		}
		System.out.println(dp[N]);
	}
}
