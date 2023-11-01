class Solution {
    int answer;
    public int solution(int[] number) {
        answer = 0;
        
        comb(0, 0, 0, number);
        
        return answer;
    }
    
    private void comb(int cnt, int start, int sum, int[] number){
        if(cnt == 3){
            if(sum == 0){
                answer++;
            }
            return;
        }
        
        for(int i=start; i<number.length; i++){
            comb(cnt+1, i+1, sum + number[i], number);
        }
    }
}