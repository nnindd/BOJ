import java.util.Arrays;
class Solution {
    public boolean solution(String[] phone_book) {
        //sort
        Arrays.sort(phone_book);
        
        //find prefix
        for(int i=1; i<phone_book.length; i++){
            if(phone_book[i].startsWith(phone_book[i-1])) return false;
        }
        
        return true;
    }
}