import java.util.*;
class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = enemy.length; //처음에는 모든 판 수행할수 있다고 표기
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i=0; i<enemy.length; i++){
            n -= enemy[i];
            pq.offer(enemy[i]); //적의 수 저장
            
            if(n < 0){ //적이 더 많기 때문에 무적권 사용하기
                if(k > 0){ //무적권을 사용할 수 있는 경우
                    k--;
                    n += pq.poll(); //적의 수만큼 복귀
                    continue; //다른 적으로 가기
                }
                //무적권을 더 사용할 수 없으면 이 턴 종료
                answer = i;
                break;
            }
        }
        
        return answer;
    }
    
}