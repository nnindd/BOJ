class Solution {
    public int solution(String s) {
        StringBuilder sb = new StringBuilder();
        String[] str = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                sb.append(s.charAt(i));
                continue;
            }
            
            for(int j=0; j<str.length; j++){
                if(s.substring(i).startsWith(str[j])){
                    sb.append(j);
                    i += str[j].length() - 1;
                }    
            }
        }
        
        return Integer.parseInt(sb.toString());
    }
}