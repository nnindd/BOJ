import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i : tangerine){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        
        // System.out.println(map.toString());
        
        List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());
        
        Collections.sort(list, new Comparator<>(){
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2){
                return o2.getValue() - o1.getValue();
            }
        });
        
        int sum = 0;
        for(Map.Entry<Integer, Integer> entry : list){
            // System.out.println(entry.getKey() + " " + entry.getValue());
            
            answer++;
            sum += entry.getValue();
            if(sum >= k){
                break;
            }
        }
        return answer;
    }
}