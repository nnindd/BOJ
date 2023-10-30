import java.util.*;
class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        String[] b = {"aya", "ye", "woo", "ma"};
        
        for(int i=0; i<babbling.length; i++){
            String one = babbling[i];
            int prev = -1;
            for(int j=0; j<b.length; j++){
                String two = b[j];
                if(one.indexOf(two+two) != -1) continue; //연속된 것
                
                else if(one.indexOf(two) != -1){
                    one = one.replace(two, "*");
                    j--;
                }
            }
            boolean flag = true;
            for(int j=0; j<one.length(); j++){
                if(one.charAt(j) != '*') {
                    flag = false;
                    break;
                }
            }
            if(flag) answer++;
        }
        
        return answer;
    }
}