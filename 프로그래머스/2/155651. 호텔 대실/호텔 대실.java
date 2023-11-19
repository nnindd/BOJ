import java.util.*;
class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        
        //전부 초로 변환
        int[][] times = new int[book_time.length][book_time[0].length];
        for(int i=0; i<times.length; i++){
            for(int j=0; j<times[0].length; j++){
                String[] str = book_time[i][j].split(":");
                int h = Integer.parseInt(str[0]);
                int m = Integer.parseInt(str[1]);
                
                times[i][j] = h * 60 + m;
                
                if(j==1) times[i][j] += 10; //청소시간 포함해서 넣어줌
            }
        }
        
        //입실 시간 빠른 기준으로 정렬
        Arrays.sort(times, (a, b) ->{
            if(a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int i=0; i<times.length; i++){
            if(pq.isEmpty()){
                answer++; //처음 객실 생성
                pq.offer(times[i][1]); //퇴실 시간 넣음
                continue;
            }
            
            if(pq.peek() <= times[i][0]){
                //현재 입실할 수 있으면 입실한다
                pq.poll();
            }else{
                answer++; //객실을 추가해주고 입실함
            }
            pq.offer(times[i][1]);
        }
        
        return answer;
    }
}