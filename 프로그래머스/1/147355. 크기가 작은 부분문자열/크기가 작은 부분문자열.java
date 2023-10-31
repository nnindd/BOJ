class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        int len = p.length();
        long pn = Long.parseLong(p);
        
        StringBuilder sb = new StringBuilder();
        
        //처음 부분 문자열 설정
        for(int i=0; i<len; i++){
            sb.append(t.charAt(i));
        }
        
        long num = Long.parseLong(sb.toString());
        if(num <= pn) answer++;
        
        for(int i=1; i<t.length() - len + 1; i++){
            sb.delete(0,1);
            sb.append(t.charAt(i+len-1));
            num = Long.parseLong(sb.toString());
            
            if(num <= pn) answer++;
        }
        
        return answer;
    }
}