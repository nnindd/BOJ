import java.util.*;
class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        
        //정렬해서 뒤에서부터 묶음
        Arrays.sort(score);
        
        for(int i=score.length-1; i>=0; i-=m){
            if(i-m + 1 < 0 ) continue; //담을 수 없는 경우
            
            int min = k;
            for(int j=0; j<m; j++){
                if(j==m-1){
                    min = score[i-j];
                }
            }
            
            answer += (min) * m;
        }
        
        return answer;
    }
}