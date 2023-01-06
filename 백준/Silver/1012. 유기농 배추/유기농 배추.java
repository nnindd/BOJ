import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K, result;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    static int[][] map;
    static boolean[][] v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            tokens = new StringTokenizer(br.readLine());
            M = Integer.parseInt(tokens.nextToken());
            N = Integer.parseInt(tokens.nextToken());
            K = Integer.parseInt(tokens.nextToken());

            map = new int[N][M];
            v = new boolean[N][M];
            result = 0;

            for (int i = 0; i < K; i++) {
                tokens = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(tokens.nextToken());
                int y = Integer.parseInt(tokens.nextToken());
                map[y][x] = 1;
            }//end input

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (!v[i][j] && map[i][j] == 1) {
                        bfs(i, j);
                        result++;
                    }
                }
            }
            System.out.println(result);
        }
    }

    private static void bfs(int x, int y) {
        //현재 위치 방문처리
        v[x][y] = true;

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int nx, ny;
            for (int d = 0; d < 4; d++) {
                nx = cur[0] + dr[d];
                ny = cur[1] + dc[d];

                if (!isRange(nx, ny)) continue;
                if (v[nx][ny]) continue;
                if (map[nx][ny] == 0) continue;

                q.offer(new int[]{nx, ny});
                v[nx][ny] = true;
            }
        }
    }

    private static boolean isRange(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= M) return false;
        return true;
    }

}
