import java.util.*;
class Solution {
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        
        int answer = -1;
        
        boolean[][] v = new boolean[n][m];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0, 0});
        v[0][0] = true;
        
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while(!q.isEmpty()){
            int[] now = q.poll();
            
            if(now[0] == n-1 && now[1] == m-1){
                answer = now[2] + 1;
                break;
            }
            
            for(int d=0; d<4; d++){
                int nx = now[0] + dir[d][0];
                int ny = now[1] + dir[d][1];
                
                if(!isRange(nx, ny, n, m) || v[nx][ny] || maps[nx][ny] == 0) continue;
                
                v[nx][ny] = true;
                q.offer(new int[]{nx, ny, now[2] + 1});
            }
        }
        
        return answer == -1 ? -1 : answer;
    }
    
    private boolean isRange(int x, int y, int n, int m){
        if(x<0 || y<0 || x>=n || y>=m) return false;
        return true;
    }
}