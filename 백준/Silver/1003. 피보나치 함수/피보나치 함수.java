import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int num;
        for (int i = 0; i < N; i++) {
            num = Integer.parseInt(br.readLine());

            //0과 1이 호출된 수를 각각 저장해준다
            int[][] dp = new int[2][num+2];

            dp[0][0] = 1; //앞은 0이 호출되는 경우, 뒤는 num이 0일때
            dp[1][1] = 1; //앞은 1이 호출되는 경우, 뒤는 num이 1일때

            if(num >= 2) {
                dp[0][2] = 1;
                dp[1][2] = 1;
            }
            for(int j=2; j<=num; j++){
                dp[0][j] = dp[0][j-1] + dp[0][j-2];
                dp[1][j] = dp[1][j-1] + dp[1][j-2];
            }

            System.out.println(dp[0][num] + " " + dp[1][num]);
        }
    }

}
