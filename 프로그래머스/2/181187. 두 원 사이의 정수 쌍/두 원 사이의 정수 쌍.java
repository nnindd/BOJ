class Solution {
    public long solution(int r1, int r2) {
        long ans = 0;
        
        for(int i=1; i<r2; i++){
            if(i<r1){
                ans += getCnt(i, r2, false) - getCnt(i, r1, true);
            }else{
                ans += getCnt(i, r2, false);
            }
        }
        
        ans *= 4;
        ans += (r2 - r1 + 1) * 4;
        return ans;
    }
    
    private int getCnt(long x, long r, boolean flag){
        double y = Math.sqrt(r*r - x*x);
        int cnt = (int) y;
        
        if(flag && y-cnt == 0.0) return cnt-1;
        return cnt;
    }
}