import java.util.*;
class Solution {
    int answer;
    public int solution(String[] board) {
        int[] start = new int[2];
        
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length(); j++){
                if(board[i].charAt(j) == 'R'){
                    start = new int[]{i, j};
                }
            }
        }
        
        return bfs(start, board);
    }
    
    private int bfs(int[] start, String[] board){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{start[0], start[1], 0});
        
        boolean[][] v = new boolean[board.length][board[0].length()];
        v[start[0]][start[1]] = true;
        
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while(!q.isEmpty()){
            int[] now = q.poll();
            
            if(board[now[0]].charAt(now[1]) == 'G'){
                return now[2];
            }
            
            for(int d=0; d<4; d++){
                int nx = now[0];
                int ny = now[1];
                
                while(true){
                    nx += dir[d][0];
                    ny += dir[d][1];
                    
                    if(!isRange(nx, ny, v.length, v[0].length) 
                       || board[nx].charAt(ny) == 'D') {
                        nx -= dir[d][0];
                        ny -= dir[d][1];
                        break;
                    }
                }
                
                if(v[nx][ny] || (now[0] == nx && now[1] == ny)) continue;
                
                v[nx][ny] = true;
                q.offer(new int[]{nx, ny, now[2] + 1});
            }
        }
        
        return -1;
    }
    
    private boolean isRange(int x, int y, int N, int M){
        if(x<0 || y<0 || x>=N || y>=M) return false;
        return true;
    }
}