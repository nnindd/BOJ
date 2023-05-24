import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] step = new int[N + 2];
        for (int i = 1; i <= N; i++) {
            step[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[N + 2];

        //1 2 3 4에서 4로 도착하는 방법
        //1->3->4
        //2->4
        dp[1] = step[1];
        dp[2] = step[1] + step[2]; //밝고 오든 안 밟고 오든 밟고오는게 더 큼

        for (int i = 3; i <= N; i++) {
            //2번째 전 계단을 밟는 경우, 3번째 전 계단을 밟고 이전 계단 밟고 오는 경우
            dp[i] = step[i] + Math.max(dp[i - 2], dp[i - 3] + step[i - 1]);
            //1234라면 1->3->4(3개 연속이 되지 않기 위해)이거나 2->4의 경로
        }

        System.out.println(dp[N]);
    }
}
