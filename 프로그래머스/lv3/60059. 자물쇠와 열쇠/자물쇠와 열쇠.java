class Solution {
    static int M, N;
    public static boolean solution(int[][] key, int[][] lock) {
        N = lock.length; //자물쇠의 크기
        M = key.length; //열쇠의 크기

        //맵을 자물쇠의 크기에서 열쇠의 크기만큼 확장해줌
        int[][] map = new int[N + (M * 2) - 2][N + (M * 2) - 2];
        
        //중심에 자물쇠 위치시키기. i/j -> map에 대한 인덱스. k/l -> 자물쇠에 대한 인덱스
        for (int i = M - 1, k = 0; i < M - 1 + N; i++, k++) {
            for (int j = M - 1, l = 0; j < M - 1 + N; j++, l++) {
                map[i][j] = lock[k][l];
            }
        }

        //임시 복사 배열
        int[][] copyMap = new int[map.length][map.length];

        //0,0부터 N-1+M까지 열쇠 두기
        for (int i = 0; i < M - 1 + N; i++) {
            for (int j = 0; j < M - 1 + N; j++) {
                for (int d = 0; d < 4; d++) {
                    //맵 복사
                    MapCopy(copyMap, map);

                    //열쇠를 복사맵에 더함
                    for (int k = 0; k < M; k++) {
                        for (int l = 0; l < M; l++) {
                            copyMap[i+k][j+l] += key[k][l];
                        }
                    }

                    //자물쇠가 열리는지 확인
                    if (check(copyMap)) { //맞는다면 트루
                        return true;
                    }
                    
                    key = rotate(key); //방향이 아니라면 돌려줌
                }
            }//end j
        }//end i for check all index

        return false;
    }
    private static void MapCopy(int[][] copyMap, int[][] map) {
        //맵 복사함
        for (int k = 0; k < map.length; k++) {
            System.arraycopy(map[k], 0, copyMap[k], 0, map[k].length);
        }
    }
    private static int[][] rotate(int[][] key) {
        int[][] copyKey = new int[M][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                copyKey[i][j] = key[M-1-j][i];
            }
        }
        return copyKey;
    }
    private static boolean check(int[][] copyMap) {
        //자물쇠 영역이 전체가 1인지 확인함
        for (int k = M - 1; k < M - 1 + N; k++) {
            for (int l = M - 1; l < M - 1 + N; l++) {
                if (copyMap[k][l] != 1) { //1이 아닌 값이 들어갔다면 돌기끼리 만났거나 홈이 안채워짐
                    return false;
                }
            }
        }
        return true;
    }
}