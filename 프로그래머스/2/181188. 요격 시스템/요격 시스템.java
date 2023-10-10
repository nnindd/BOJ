import java.util.Arrays;
class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (a, b)->{return a[1] - b[1];});
        
        //그리디
        //끝점 기준으로 오름차순 정렬
        //다음 시작점이 기준점 내라면 넘어감
        //아니라면 끝점을 현재 끝점으로 갱신, 정답 증가
        int x = targets[0][0];
        int y = targets[0][1];
        int answer = 1;
        
        for(int i=1; i<targets.length; i++){
            int s = targets[i][0];
            int e = targets[i][1];
            
            if(s < y) continue;
            
            answer++;
            y = e;
        }
        return answer;
    }
}