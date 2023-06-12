import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokens.nextToken());
        int M = Integer.parseInt(tokens.nextToken());
        int[][] map = new int[N][M];

        //0 빈공간, 1 벽, 2 사람
        int r = -1;
        int c = -1;
        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            for (int j = 0; j < M; j++) {
                if (temp.charAt(j) == 'P') {
                    map[i][j] = 2;
                } else if (temp.charAt(j) == 'X') {
                    map[i][j] = 1;
                } else if (temp.charAt(j) == 'I') {
                    r = i;
                    c = j;
                    map[i][j] = 0;
                } else {
                    map[i][j] = 0;
                }
            }
        }//end input

        int res = bfs(map, r, c);
        System.out.println(res == 0 ? "TT" : res);
    }

    private static int bfs(int[][] map, int r, int c) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r, c});

        boolean[][] v = new boolean[map.length][map[0].length];
        v[r][c] = true;

        int res = 0;
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dir[d][0];
                int ny = cur[1] + dir[d][1];

                if (!isRange(nx, ny, map.length, map[0].length)
                        || v[nx][ny]
                        || map[nx][ny] == 1) continue;

                if (map[nx][ny] == 2) {
                    res++;
                }

                v[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }

        }
        return res;
    }

    private static boolean isRange(int x, int y, int N, int M) {
        if (x < 0 || y < 0 || x >= N || y >= M) return false;
        return true;
    }
}
