import java.util.*;

class Solution {
    public int[] solution(String[] park, String[] routes) {
        int x = 0, y = 0;
        for(int i=0; i<park.length; i++){
            for(int j=0; j<park[i].length(); j++){
                if(park[i].charAt(j) == 'S'){
                    x = i;
                    y = j;
                }
            }
        }
        
        
        for(int i=0; i<routes.length; i++){
            int[] res = move(x, y, routes[i].charAt(0), routes[i].charAt(2)-'0', park);
            x = res[0];
            y = res[1];
        }
        
        return new int[]{x, y};
    }
    
    private int[] move(int x, int y, char d, int len, String[] park){
        int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        int dir = getDir(d);
        
        boolean flag = true;
        int nx = x;
        int ny = y;
        
        for(int i=0; i<len; i++){
            nx += direction[dir][0];
            ny += direction[dir][1];
            
            if(!isRange(nx, ny, park.length, park[i].length()) || park[nx].charAt(ny) == 'X') {
                flag = false;
                break;
            }
        }
        
        return flag ? new int[]{nx, ny} : new int[]{x, y};
    }
    
    private boolean isRange(int x, int y, int N, int M){
        if(x<0 || y<0 || x>=N || y>=M) return false;
        return true;
    }
    
    private int getDir(char d){
        if(d == 'N'){
            return 0;
        }else if(d == 'S'){
            return 1;
        }else if(d == 'W'){
            return 2;
        }else{
            return 3;
        }
    }
}