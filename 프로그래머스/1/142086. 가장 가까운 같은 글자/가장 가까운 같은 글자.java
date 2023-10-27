import java.util.Arrays;
class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        int[] cnt = new int['z' - 'a' + 1];
        
        Arrays.fill(cnt, -1);
        
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            int idx = c - 'a';
            
            if(cnt[idx] != -1){
                answer[i] = i - cnt[idx];
                cnt[idx] = i;
            }else{
                answer[i] = -1;
                cnt[idx] = i;
            }
        }
        
        return answer;
    }
}