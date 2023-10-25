class Solution {
    public int solution(String s) {
        int answer = 0;
        int cnt1 = 0, cnt2 = 0;
        char prev = s.charAt(0);
        
        for(int i=0; i<s.length(); i++){
            char next = s.charAt(i);
            
            if(prev == next) cnt1++;
            else cnt2++;
            
            if(cnt1==cnt2){
                answer++;
                cnt1 = 0;
                cnt2 = 0;
                if(i+1 < s.length()){
                    prev = s.charAt(i+1);
                }
            }else if(i == s.length()-1) answer++;
        }
        return answer;
    }
}