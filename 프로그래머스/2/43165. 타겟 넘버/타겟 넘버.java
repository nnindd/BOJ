class Solution {
    int answer, numbers[], target;
    public int solution(int[] numbers, int target) {
        answer = 0;
        this.numbers = numbers.clone();
        this.target = target;
        
        dfs(0, 0);
        
        return answer;
    }
    
    private void dfs(int cnt, int sum){
        if(cnt == numbers.length){
            if(sum == target){
                answer++;
            }
            return;
        }
        
        dfs(cnt+1, sum - numbers[cnt]);
        dfs(cnt+1, sum + numbers[cnt]);
    }
    
}