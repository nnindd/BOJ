import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        
        HashMap<String, Integer> map = new HashMap<>(); //순서 저장
        for(int i=0; i<players.length; i++){
            map.put(players[i], i);
        }
        
        int idx;
        for(int i=0; i<callings.length; i++){
            idx = map.get(callings[i]); //순서 가져옴
            
            //순서 변경
            players[idx] = players[idx-1]; //뒤로 밀려남
            players[idx-1] = callings[i]; //추월 정보
            
            //map에서 순서 바꿔줌
            map.replace(players[idx], idx);
            map.replace(players[idx-1], idx-1);
            
        }
        
        return players;
    }
}