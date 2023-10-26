class Solution {
    public int[] solution(String[] wallpaper) {
        int sx, sy, ex, ey;
        
        sx = sy = Integer.MAX_VALUE;
        ex = ey = Integer.MIN_VALUE;
        
        for(int i=0; i<wallpaper.length; i++){
            for(int j=0; j<wallpaper[0].length(); j++){
                if(wallpaper[i].charAt(j) == '#'){
                    sx = Math.min(sx, i);
                    sy = Math.min(sy, j);
                    ex = Math.max(ex, i);
                    ey = Math.max(ey, j);
                }
            }
        }
        
        return new int[]{sx, sy, ex+1, ey+1};
    }
}