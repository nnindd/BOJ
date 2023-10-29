class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        
        int cnt = 0;
        for(int i=0; i<s.length(); i++){
            String str = s.substring(i, i+1);
            
            if(str.equals(" ")) {
                sb.append(" ");
                cnt = 0;
                continue;
            }
            
            if(cnt % 2 == 0){
                sb.append(str.toUpperCase());
            }else{
                sb.append(str.toLowerCase());
            }
            cnt++;
        }
        return sb.toString();
    }
}