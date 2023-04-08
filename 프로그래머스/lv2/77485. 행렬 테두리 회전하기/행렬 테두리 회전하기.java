import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        
        int[][] map = new int[rows+1][columns+1];
        int nums = 1;
        for(int i=1; i<=rows; i++){
            for(int j=1; j<=columns; j++){
                map[i][j] = nums++;
            }
        }//end map
        
        int sx, sy, ex, ey; //시작xy, 끝xy
        //회전 횟수만큼 반복
        for(int i=0; i<queries.length; i++){
            // int min = Integer.MAX_VALUE;
            List<Integer> list = new ArrayList<>();
            
            sx = queries[i][0];
            sy = queries[i][1];
            ex = queries[i][2];
            ey = queries[i][3];
            
            int temp = map[sx][sy]; //처음 값 임시 저장
            list.add(temp);

            //left row
            int ix = sx;
            int iy = sy;
            while(ix < ex){
                map[ix][iy] = map[++ix][iy];
                list.add(map[ix][iy]);
            }
            
            //under col
            ix = ex;
            iy = sy;
            while(iy < ey){
                map[ix][iy] = map[ix][++iy];
                list.add(map[ix][iy]);
            }
            
            //right row
            ix = ex;
            iy = ey;
            while(ix > sx){
                map[ix][iy] = map[--ix][iy];
                list.add(map[ix][iy]);
            }
            
            //up col
            ix = sx;
            iy = ey;
            while(iy > sy){
                map[ix][iy] = map[ix][--iy];
                list.add(map[ix][iy]);
            }
            
            //save temp
            map[sx][sy+1] = temp;
            
            Collections.sort(list);
            answer[i] = list.get(0);
            
            // System.out.println(i+"번째 회전");
            // for(int k=1; k<=rows; k++){
            //     for(int j=1; j<=columns; j++){
            //         System.out.printf("%3d", map[k][j]);
            //     }
            //     System.out.println();
            // }
            
        }        
        
        return answer;
    }
}