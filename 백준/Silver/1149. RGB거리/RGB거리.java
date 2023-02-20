import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n+1][3];
		
		for(int i=1; i<=n; i++) {
			StringTokenizer tokens = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(tokens.nextToken()); //RED
			map[i][1] = Integer.parseInt(tokens.nextToken()); //GREEN
			map[i][2] = Integer.parseInt(tokens.nextToken()); //BLUE
		}
		
		//N-1번집 != N번집
		//i-1번집 != i번집 != i+1번집
		//다음집만 다른 색이면 됨
		
		//N=1 => 가장 작은 값 색칠
		//N=2 => min(1색칠+남은색 중 가장 작은값, 1에서 두번째 작은 값+그색과 다른것중 작은 값)
		
		int[][] dp = new int[n+1][3]; //최소로 칠한 것을 저장
		dp[0][0] = dp[0][1] = dp[0][2] = 0; //초기값
		for(int i=1; i<=n; i++) {
			//해당 영역이 아닌 곳의 최소값과 현재 영역의 값 저장하기
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + map[i][0];
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + map[i][1];
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + map[i][2];
		}
		//마지막 집에서 최소값을 찾아 출력한다
		System.out.println(Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2])));
		
	}

}
