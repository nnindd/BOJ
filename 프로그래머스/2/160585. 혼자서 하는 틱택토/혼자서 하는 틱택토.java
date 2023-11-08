class Solution {
    public int solution(String[] board) {
        //안되는 경우
        int o = 0, x = 0;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board[i].charAt(j) == 'O') o++;
                else if(board[i].charAt(j) == 'X') x++;
            }
        }

        //1) o보다 x의 개수가 더 많음
        if(o < x) return 0;
        //2) o와 x의 개수 차이가 2 이상이 남
        if(o - x > 1) return 0;
        
        boolean of = win('O', board, "OOO");
        boolean xf = win('X', board, "XXX");
        
        //1) O와 X 중 하나만 성공해야 함
        if(of && xf) return 0;
        
        //2) O로 완성했을 때 X는 O의 개수보다 작아야 함
        if(of && !xf && x != o-1) return 0;
        
        //3) X로 완성했을 때 OX 개수 동일
        if(!of && xf && o != x) return 0;
        
        return 1;
    }
    
    private boolean win(char c, String[] b, String str){
        //가로
        for(int i=0; i<3; i++){
            if(b[i].equals(str)) return true;
        }
        
        //세로
        for(int j=0; j<3; j++){
            if(b[0].charAt(j) == c && b[1].charAt(j) == c && b[2].charAt(j) == c) return true;
        }
        
        //대각선
        if(b[0].charAt(0) == c && b[1].charAt(1) == c && b[2].charAt(2) == c) return true;
        if(b[0].charAt(2) == c && b[1].charAt(1) == c && b[2].charAt(0) == c) return true;
        
        return false;
    }
}