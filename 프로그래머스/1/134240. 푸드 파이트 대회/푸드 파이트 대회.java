class Solution {
    public String solution(int[] food) {
        StringBuilder sb = new StringBuilder();
        
        for(int i=1; i<food.length; i++){
            if(food[i]%2==1) food[i]--;
            
            for(int j=0; j<food[i]/2; j++){
                sb.append(i);
            }
        }
        String answer = sb.toString() + "0" + sb.reverse();
        
        return answer;
    }
}