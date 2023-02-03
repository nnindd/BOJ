import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    static class Pos {
        int x;
        int y;
        int cnt;

        public Pos(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        map = new int[N][M];
        String temp;
        for (int i = 0; i < N; i++) {
            temp = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = temp.charAt(j) - '0';
            }
        }//end input

        bfs();
    }

    private static void bfs() {
        Queue<Pos> q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][M];

        q.offer(new Pos(0, 0, 1));
        v[0][0] = true;

        Pos cur;
        while (!q.isEmpty()) {
            cur = q.poll();

            if (cur.x == N - 1 && cur.y == M - 1) {
                System.out.println(cur.cnt);
                return;
            }
            int nx, ny;
            for (int d = 0; d < 4; d++) {
                nx = cur.x + dr[d];
                ny = cur.y + dc[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if (v[nx][ny]) continue;
                if (map[nx][ny] == 0) continue;

                v[nx][ny] = true;
                q.offer(new Pos(nx, ny, cur.cnt+1));
            }
        }
    }
}
