import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int l = Integer.parseInt(br.readLine());

            int[][] map = new int[l][l];

            tokens = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(tokens.nextToken());
            int y1 = Integer.parseInt(tokens.nextToken());

            tokens = new StringTokenizer(br.readLine());
            int x2 = Integer.parseInt(tokens.nextToken());
            int y2 = Integer.parseInt(tokens.nextToken());

            //나이트의 이동 방향 8방
            System.out.println(bfs(x1, y1, x2, y2, l));
        }
    }

    private static int bfs(int x1, int y1, int x2, int y2, int l) {
        int res = -1;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x1, y1, 0});

        boolean[][] v = new boolean[l][l];
        v[x1][y1] = true;

        while (!q.isEmpty()){
            int[] c = q.poll();

            if(c[0] == x2 && c[1] == y2){
                res = c[2];
                break;
            }

            for (int d = 0; d < 8; d++) {
                int nx = c[0] + dir[d][0];
                int ny = c[1] + dir[d][1];

                if(!isRange(nx, ny, l) || v[nx][ny]) continue;
                v[nx][ny] = true;
                q.offer(new int[]{nx, ny, c[2]+1});
            }
        }
        return res;
    }


    static int[][] dir = {
            {-2, -1}, {-2, 1},
            {-1, -2}, {1, -2},
            {2, -1}, {2, 1},
            {-1, 2}, {1, 2}};

    private static boolean isRange(int x, int y, int l) {
        if (x < 0 || x >= l || y < 0 || y >= l) return false;
        return true;
    }
}
