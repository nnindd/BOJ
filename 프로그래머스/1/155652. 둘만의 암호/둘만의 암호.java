import java.util.*;
class Solution {
    public String solution(String s, String skip, int index) {
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            
            int idx = 0;
            while(idx < index){
                c += 1;
                if(c > 'z') c = 'a';
                if(skip.contains(c+"")) continue;
                idx++;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}