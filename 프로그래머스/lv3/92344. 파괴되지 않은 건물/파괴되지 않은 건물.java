class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        int[][] map = new int[board.length+1][board[0].length+1];
        
        int type, r1, c1, r2, c2, degree;
        
        //skill 진행
        for(int i=0; i<skill.length; i++){ 
            type = skill[i][0];
            r1 = skill[i][1];
            c1 = skill[i][2];
            r2 = skill[i][3];
            c2 = skill[i][4];
            degree = skill[i][5];
            
            if(type==1){ //공격
                map[r1][c1] -= degree; //시작위치
                map[r1][c2+1] += degree; //시작행의 끝
                map[r2+1][c1] += degree; //시작열의 끝
                map[r2+1][c2+1] -= degree; //끝행열 +1
            }else{ //회복
               map[r1][c1] += degree; //시작위치
                map[r1][c2+1] -= degree; //시작행의 끝
                map[r2+1][c1] -= degree; //시작열의 끝
                map[r2+1][c2+1] += degree; //끝행열 +1 
            }
        }
        
        int temp;
        
        //가로 누적합 구하기
        for(int i=0; i<map.length; i++){
            temp = map[i][0];
            for(int j=1; j<map[0].length; j++){
                map[i][j] += temp;
                temp = map[i][j];
            }
        }
        
        //세로 누적합 구하기
        for(int j=0; j<map[0].length; j++){
            temp = map[0][j];
            for(int i=1; i<map.length; i++){
                map[i][j] += temp;
                temp = map[i][j];
            }
        }
        
        
        
        //원래 맵에서 계산
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                map[i][j] += board[i][j];
                if(map[i][j] > 0){
                    answer++;
                }
            }
        }
        
        return answer;
    }
    
}