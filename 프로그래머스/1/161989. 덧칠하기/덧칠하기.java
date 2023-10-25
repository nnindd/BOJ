class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 1; //처음 칠함
        int start = section[0];
        
        for(int s : section){
            if(start + m > s) 
                continue; //칠할 수 있음
            
            answer++;
            start = s; //새로 칠할 구간
        }
        return answer;
    }
}