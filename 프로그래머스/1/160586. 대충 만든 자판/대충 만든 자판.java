import java.util.HashMap;
class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        
        HashMap<Character, Integer> map = new HashMap<>();
        for(String str : keymap){
            for(int i=0; i<str.length(); i++){
                char c = str.charAt(i);
                
                if(map.containsKey(c)) {
                    map.put(c, Math.min(i+1, map.get(c)));
                }else{
                    map.put(c, i+1);
                }
            }
        }
        
        for(int idx = 0; idx<targets.length; idx++){
            int cnt = 0;
            boolean flag = true;
            for(int i=0; i<targets[idx].length(); i++){
                char c = targets[idx].charAt(i);
                
                if(!map.containsKey(c)) {
                    flag = false;
                    break;
                }
                
                cnt += map.get(c);
            }
            
            if(flag){
                answer[idx] = cnt;
            }else{
                answer[idx] = -1;
            }
        }
        return answer;
    }
}