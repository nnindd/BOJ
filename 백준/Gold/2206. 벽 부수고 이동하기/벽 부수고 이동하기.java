import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        map = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            for (int j = 0; j < M; j++) {
                if (temp.charAt(j) == '1') {
                    map[i][j] = true;
                }
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        int min = -1;

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0, 0, 0}); //x,y, 이동거리수, 벽 부심 여부

        boolean[][][] v = new boolean[N][M][2];
        v[0][0][0] = true; //벽을 안 부순 상태에서 방문체크

        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (cur[0] == N - 1 && cur[1] == M - 1) {
                return cur[2] + 1;
            }

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dir[d][0];
                int ny = cur[1] + dir[d][1];

                if (!isRange(nx, ny)) continue;

                if (!map[nx][ny]) {//벽이 아닌 경우
                    //부신 적 없음
                    if(cur[3] == 0 && !v[nx][ny][0]){
                        q.offer(new int[]{nx, ny, cur[2]+1, 0});
                        v[nx][ny][0] = true;
                    }else if(cur[3] == 1 && !v[nx][ny][1]){//부신 적 있음
                        q.offer(new int[]{nx, ny, cur[2]+1, 1});
                        v[nx][ny][1] = true;
                    }
                } else {//벽인 경우
                    //벽을 부신적이 없으면 부셔줌
                    if (cur[3] == 0 && !v[nx][ny][0]) {
                        q.offer(new int[]{nx, ny, cur[2] + 1, 1});
                        v[nx][ny][1] = true;
                    }
                }
            }
        }
        return min;
    }

    private static boolean isRange(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= M) return false;
        return true;
    }
}
