class Solution {
    public String solution(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length(); i++){
            int c = s.charAt(i);
            
            if('a' <= c && c <= 'z'){
                c += n;
                if(c > 'z'){
                    c = 'a' - 1 + c - 'z';
                }
            }else if('A' <= c && c <= 'Z'){
                c += n;
                if(c > 'Z'){
                    c = 'A' - 1 + c - 'Z';
                }
                
            }
            sb.append((char)c);
        }
        return sb.toString();
    }
}