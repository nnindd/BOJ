class Solution {
    public int solution(String s) {
        StringBuilder sb = new StringBuilder();
        String[] str = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        
        for(int i=0; i<str.length; i++){
            s = s.replaceAll(str[i], Integer.toString(i));
        }
        return Integer.parseInt(s);
    }
}