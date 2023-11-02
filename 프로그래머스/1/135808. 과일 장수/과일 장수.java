import java.util.*;
class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        
        //정렬해서 하나로 묶이는 단위의 최소값만 가져옴
        Arrays.sort(score);
        
        for(int i=score.length-m; i>=0; i-=m){
            answer += score[i] * m;
        }
        return answer;
    }
}