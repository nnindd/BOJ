import java.util.*;
class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        ArrayList<Integer> list = new ArrayList<>();
        
        StringTokenizer tokens = new StringTokenizer(today, ".");
        int todayTime = convertTime(Integer.parseInt(tokens.nextToken()),
                                   Integer.parseInt(tokens.nextToken()),
                                   Integer.parseInt(tokens.nextToken()));
        
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0; i<terms.length; i++){
            tokens = new StringTokenizer(terms[i]);
            map.put(tokens.nextToken(), Integer.parseInt(tokens.nextToken()));
        }
        
        for(int i=0; i<privacies.length; i++){
            tokens = new StringTokenizer(privacies[i]);
            String str = tokens.nextToken();
            String term = tokens.nextToken();
            
            tokens = new StringTokenizer(str, ".");
            int p = convertTime(Integer.parseInt(tokens.nextToken()),
                                   Integer.parseInt(tokens.nextToken()),
                                   Integer.parseInt(tokens.nextToken()));
            p += map.get(term) * 28;
            
            // System.out.println(todayTime+" " +p);
            
            if(p <= todayTime){
                list.add(i+1);
            }
        }
        
        return list.stream().mapToInt(i->i).toArray();
    }
    
    private int convertTime(int y, int m, int d){ //일자로 변환
        // System.out.println(y+" " + m +" " + d);
        return y * 12 * 28 + m * 28 + d;
    }

}