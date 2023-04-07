import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long hap1 = 0, hap2 = 0;
        Deque<Integer> q1 = new ArrayDeque<>();
        Deque<Integer> q2 = new ArrayDeque<>();
        
        for(int i=0; i<queue1.length; i++){
            hap1 += queue1[i];
            hap2 += queue2[i];
            q1.offer(queue1[i]);
            q2.offer(queue2[i]);
        }
        
        int cnt = 0;
        
        while(hap1!=hap2){
            cnt++;
            
            int temp;
            if(hap1>hap2){
                temp = q1.poll();
                q2.offer(temp);
                hap1 -= temp;
                hap2 += temp;
            }else{
                temp = q2.poll();
                q1.offer(temp);
                hap1 += temp;
                hap2 -= temp;
            }
            
            if(cnt > queue1.length*4) return -1;
        }
        
        return cnt;
    }
}