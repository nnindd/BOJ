import java.util.*;
class Solution {
    int answer, board[][];
    Stack<Integer> stack;
    public int solution(int[][] board, int[] moves) {
        answer = 0;
        int len = board.length;
        
        this.board = board;
        stack = new Stack<>();
        
        for(int col : moves){
            findDoll(col-1, 0);
        }
        
        return answer;
    }
    
    private void findDoll(int col, int row){
        if(row == board.length) {
            return;
        }else if(board[row][col] != 0){
            getDoll(board[row][col]);
            board[row][col] = 0;
            return;
        }
        findDoll(col, row+1); //다음 행 탐색
    }
    private void getDoll(int num){
        if(!stack.isEmpty() && stack.peek() == num){
            answer += 2;
            stack.pop();
        }else{
            stack.push(num);
        }
    }
}
/*
    0 0 0 0 0
    0 0 1 0 3
    0 2 5 0 1
    4 2 4 4 2
    3 5 1 3 1
    
    0 0 0 0 0
    0 0 0 0 0
    0 2 5 0 1
    0 2 4 4 2
    3 5 1 3 1
    
    0, 4, 2, 4, 0, 1, 0, 3
    4 3 1
*/