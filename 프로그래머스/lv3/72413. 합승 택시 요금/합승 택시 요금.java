import java.util.*;
class Solution {
    static final int INF = 999_999_999;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = INF;
        
        int[][] dp = new int[n+1][n+1];
        //INF로 초기화
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                dp[i][j] = INF;
            }
        }
        
        //거리 초기값
        for(int i=0; i<fares.length; i++){
            dp[fares[i][0]][fares[i][1]] = fares[i][2];
            dp[fares[i][1]][fares[i][0]] = fares[i][2];
        }
        
        //플로이드 워셜
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    if(i==j) dp[i][j] = 0;
                    dp[i][j] = Math.min(dp[i][j], dp[i][k]+dp[k][j]);
                }
            }
        }
        
        for(int i=1; i<=n; i++){
            if(dp[s][i]!=INF && dp[i][a]!=INF && dp[i][b]!=INF){
                answer = Math.min(answer, dp[s][i]+dp[i][a]+dp[i][b]);
            }
        }
        
        return answer;
    }
}