class Solution {
    int answer;
    public int solution(int k, int[][] dungeons) {
        answer = 0;
        
        //순열 구하기
        int len = dungeons.length;
        perm(0, new int[len][2], new boolean[len], k, dungeons);
        
        return answer;
    }
    
    private void perm(int cnt, int[][] s, boolean[] v, int k, int[][] dungeons){
        //던전 돌 순서를 정함
        if(cnt == s.length){
            answer = Math.max(answer, play(s, k)); //정해진 순서대로 플레이
            return;
        }
        
        for(int i=0; i<s.length; i++){
            if(v[i]) continue;
            v[i] = true;
            s[cnt] = dungeons[i];
            perm(cnt + 1, s, v, k, dungeons);
            v[i] = false;
        }
    }
    
    private int play(int[][] s, int k){
        //플레이를 모두 할 수 있으면 회수 돌려줌
        int res = 0;
        for(int i=0; i<s.length; i++){
            if(k >= s[i][0]){
                res++;
                k -= s[i][1];
            }
        }
        return res;
    }
}
