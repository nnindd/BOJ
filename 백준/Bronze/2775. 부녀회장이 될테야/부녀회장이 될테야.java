import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		int k, n;
		int[][] dp = new int[15][15]; // [층][호] 해당 층의 인원 저장

		while (T-- > 0) {
			k = Integer.parseInt(br.readLine());
			n = Integer.parseInt(br.readLine());

			for (int i = 0; i <= k; i++) {// 층만큼 진행
				for (int j = 1; j <= n; j++) {// 호수만큼 진행
					if(i==0) { //0층이라면
						dp[i][j] = j; //현재 호수 저장
					}else if(j==1) { //1호라면 1 저장
						dp[i][j] = 1;
					}else {
						dp[i][j] = dp[i][j-1] + dp[i-1][j];//전 호수 인원수 + 아래 호수 인원수 
					}
				}
			}
			sb.append(dp[k][n]).append("\n");
		}
		System.out.println(sb);
	}
}
