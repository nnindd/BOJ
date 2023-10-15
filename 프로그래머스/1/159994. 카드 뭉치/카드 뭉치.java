import java.util.*;
class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        Queue<String> q1 = new ArrayDeque<>();
        Queue<String> q2 = new ArrayDeque<>();
        
        for(String str : cards1){
            q1.offer(str);
        }
        
        for(String str : cards2){
            q2.offer(str);
        }
        
        int cnt = 0;
        
        for(String str : goal){
            if(!q1.isEmpty() && q1.peek().equals(str)){
                q1.poll();
                cnt++;
            }
            
            if(!q2.isEmpty() && q2.peek().equals(str)){
                q2.poll();
                cnt++;
            }
        }
        
        if(cnt == goal.length) return "Yes";
        return "No";
    }
}