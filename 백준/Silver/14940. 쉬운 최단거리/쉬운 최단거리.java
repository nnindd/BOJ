import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, map[][], res[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        map = new int[N][M];
        res = new int[N][M];

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
                if (map[i][j] == 2) {
                    q.offer(new int[]{i, j, 0});
                    v[i][j] = true;
                    map[i][j] = 0;
                }
            }
        }

        bfs(q, v);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                //map은 1인데 res에 0으로 되어있으면 도달하지 못함
                if (map[i][j] == 1 && res[i][j] == 0) {
                    sb.append(-1);
                } else {
                    sb.append(res[i][j]);
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);

    }

    private static void bfs(Queue<int[]> q, boolean[][] v) {
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dir[d][0];
                int ny = cur[1] + dir[d][1];

                if (!isRange(nx, ny) || v[nx][ny] || map[nx][ny] == 0) continue;

                res[nx][ny] = cur[2] + 1;
                v[nx][ny] = true;
                q.offer(new int[]{nx, ny, cur[2] + 1});
            }
        }
    }

    private static boolean isRange(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= M) return false;
        return true;
    }
}
