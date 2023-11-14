import java.util.*;
class Solution {
    int[][] map;
    public int solution(String[] maps) {
        map = new int[maps.length][maps[0].length()];
        int[] start = new int[3];
        
        for(int i=0; i<maps.length; i++){
            for(int j=0; j<maps[i].length(); j++){
                if(maps[i].charAt(j) == 'O') continue;
                
                else if(maps[i].charAt(j) == 'S'){
                    start = new int[]{i, j, 0};
                }else if(maps[i].charAt(j) == 'E'){
                    map[i][j] = 9; //출구
                }else if(maps[i].charAt(j) == 'L'){
                    map[i][j] = 8; //레버
                }else{
                    map[i][j] = 1;
                }
            }
        }
        
        return play(start);
    }
    
    private int play(int[] start){
        //레버 구하기
        int[] answer = bfs(start, 8);
        
        if(answer[0] == -1 && answer[1] == -1) return -1;
        
        //출구 구하기
        answer = bfs(answer, 9);
        
        if(answer[0] == -1 && answer[1] == -1) return -1;
        
        return answer[2];
    }
    
    private int[] bfs(int[] start, int num){
        int[] answer = {-1, -1, -1};
        
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(start);
        
        boolean[][] v = new boolean[map.length][map[0].length];
        v[start[0]][start[1]] = true;
        
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while(!q.isEmpty()){
            int[] now = q.poll();
            
            if(map[now[0]][now[1]] == num){
                answer = now;
                break;
            }
            
            for(int d=0; d<4; d++){
                int nx = now[0] + dir[d][0];
                int ny = now[1] + dir[d][1];
                
                if(!isRange(nx, ny, map.length, map[0].length)
                  || v[nx][ny] || map[nx][ny] == 1) continue;
                
                v[nx][ny] = true;
                q.offer(new int[]{nx, ny, now[2] + 1});
            }
        }
        
        return answer;
    }
    
    private boolean isRange(int x, int y, int n, int m){
        if(x<0 || y<0 || x>=n || y>=m) return false;
        return true;
    }
}