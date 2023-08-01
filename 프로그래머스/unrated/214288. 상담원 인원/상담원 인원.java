import java.util.*;

class Solution {
    int k, n, reqs[][], result;

    public int solution(int k, int n, int[][] reqs){
        this.k = k;
        this.n = n;
        this.reqs = reqs.clone();

        //상담원을 배정함
        int[] mento = new int[k];
        Arrays.fill(mento, 1);

        result = Integer.MAX_VALUE;
        dfs(0, n-k, mento);
        return result;
    }

    private void dfs(int dept, int limit, int[] mento){
        if(dept == k || limit == 0){
            //상담 인원에 따른 대기시간 계산
            //최소값 갱신
            result = Math.min(result, getWaitTime(mento));
            return;
        }

        for(int i=0; i<=limit; i++){
            mento[dept] += i;
            dfs(dept+1, limit - i, mento);
            mento[dept] -= i;
        }
    }

    private int getWaitTime(int[] mento){
        //상담에 배정된 인원에 맞춰서 대기시간 계산
        
        //유형별로 대기 인원 분리
        ArrayList<ArrayList<int[]>> list = new ArrayList<>();
        for(int i=0; i<k; i++){
            list.add(new ArrayList<>());
        }

        for(int i=0; i<reqs.length; i++){
            int want = reqs[i][2] - 1;
            list.get(want).add(new int[]{reqs[i][0], reqs[i][1]});
        }

        int total = 0;
        for(int i=0; i<k; i++){
            total += getWaitTimeForThisType(list.get(i), mento[i]);
        }

        return total;
    }

    private int getWaitTimeForThisType(ArrayList<int[]> list, int limit){
        //현재 유형의 대기인원들과 상담 제한수
        int wait = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i=0; i<list.size(); i++){
            int[] cur = list.get(i);

            if(i==0){
                limit--; //한명 이미 배정됨
                pq.offer(cur[0]+cur[1]); //끝나는 시간 추가
                continue;
            }
            
            if(pq.isEmpty()){
                //진행중인 상담이 없는 경우 그냥 지금 자기 추가
                pq.offer(cur[0]+cur[1]);
                continue;
            }

            //최소로 끝나는 시간과 자신 비교
            if(pq.peek() < cur[0]){
                //자신이 더 큰 경우 대기 시간 없이 바로 상담 가능
                pq.poll();
                pq.offer(cur[0]+cur[1]);
                continue;
            }

            //대기해야 하는 경우
            //1) 상담원 추가
            if(limit > 0){
                pq.offer(cur[0] + cur[1]);
                limit--;
            }else{//2) 상담원 추가 ㄴㄴ
                int temp = pq.poll();
                wait += temp - cur[0];
                pq.offer(temp + cur[1]);
            }
        }
        return wait;
    }
}