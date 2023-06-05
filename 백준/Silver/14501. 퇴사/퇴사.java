import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;

        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N + 1]; //i일의 상담기간
        int[] P = new int[N + 1]; //i일의 페이

        for (int i = 1; i <= N; i++) {
            tokens = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(tokens.nextToken()); //상담 기간 저장
            P[i] = Integer.parseInt(tokens.nextToken()); //상담 페이 저장
        }

        //i일에 상담을 하냐, 안 하냐
        int[] dp = new int[N + 2];
        for (int i = N; i > 0; i--) {
            if (i + T[i] > N + 1) {
                //현재 날짜의 상담일이 상담 종료일을 넘는 경우
                //이 상담을 안 하고 더 앞의 날짜로 선택
                dp[i] = dp[i + 1];
            } else {
                //상담이 가능함
                //dp[i+1] 이 상담 선택 안 할 때
                //dp[i+T[i]] + P[i] 이 상담 선택할때
                //둘 중 큰 값 선택
                dp[i] = Math.max(dp[i + T[i]] + P[i], dp[i + 1]);
            }
        }
        System.out.println(dp[1]);

    }

}
