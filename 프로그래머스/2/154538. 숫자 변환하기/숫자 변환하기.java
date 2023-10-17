import java.util.Arrays;
class Solution {
    int answer;
    public int solution(int x, int y, int n) {
        int[] dp = new int[y+1];
        final int INF = 1000001;
        Arrays.fill(dp, INF);
        
        dp[x] = 0;
        for(int i=x; i<=y; i++){
            if(i*2 <= y){
                dp[i*2] = Math.min(dp[i*2], dp[i]+1);
            }
            if(i*3 <= y){
                dp[i*3] = Math.min(dp[i*3], dp[i]+1);
            }
            if(i+n <= y){
                dp[i+n] = Math.min(dp[i+n], dp[i]+1);
            }
        }
        
        return dp[y] >= INF ? -1 : dp[y];
    }
    
    
}