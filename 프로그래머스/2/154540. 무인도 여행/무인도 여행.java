import java.util.*;
class Solution {
    String[] maps;
    public int[] solution(String[] maps) {
        this.maps = maps.clone();
        boolean[][] v = new boolean[maps.length][maps[0].length()];
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int i=0; i<maps.length; i++){
            for(int j=0; j<maps[i].length(); j++){
                if(maps[i].charAt(j) == 'X' || v[i][j]) continue;
                
                v[i][j] = true;
                list.add(bfs(i, j, v));
            }
        }
        
        if(list.size() == 0){
            return new int[]{-1};
        }else{
            Collections.sort(list);
            return list.stream().mapToInt(i->i).toArray();
        }
    }
    
    int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    private int bfs(int i, int j, boolean[][] v){
        int answer = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{i, j});
        
        while(!q.isEmpty()){
            int[] now = q.poll();
            
            answer += maps[now[0]].charAt(now[1]) - '0';
            
            for(int d=0; d<4; d++){
                int nx = now[0] + dir[d][0];
                int ny = now[1] + dir[d][1];
                
                if(nx<0 || ny<0 || nx>=v.length || ny>=v[0].length || v[nx][ny] || maps[nx].charAt(ny) == 'X') continue;
                
                v[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }
        return answer;
    }
}