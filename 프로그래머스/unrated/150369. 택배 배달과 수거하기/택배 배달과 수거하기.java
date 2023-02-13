class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        int dbox = 0, pbox = 0; //배달, 픽업 허용치까지 담을 변수
        
        for(int i=n-1; i>=0; i--){ //가장 먼 집부터 탐색
            if(deliveries[i] == 0 && pickups[i] == 0) continue; //배달, 수거할게 없으면 안 함
            
            //지금 집에서 배달 픽업 할 수 있는 상자를 다 더함
            dbox += deliveries[i];
            pbox += pickups[i];
            
            while(dbox > 0 || pbox > 0){
                //허용치까지 반복해서 방문. 음수가 되면 i번째 집까지 오는 길에 앞의 집까지 한번에 처리 가능하다는 뜻
                dbox -= cap;
                pbox -= cap;
                answer += (i+1) * 2; //i번째집 * 2(왕복)
            }
        }
        
        return answer;
    }
}