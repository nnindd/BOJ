import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, res;
    static int[][] map;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        map = new int[N][M];
        res = 0;

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }

        boolean[][] v = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                v[i][j] = true;
                dfs(0, 0, i, j, v); //4개 체크
                v[i][j] = false;
                anotherShape(i, j); // ㅗ ㅜ ㅓ ㅏ 모양 체크
            }
        }
        System.out.println(res);

    }
    
    private static void dfs(int cnt, int sum, int x, int y, boolean[][] v) {
        if (cnt == 4) {
            res = Math.max(res, sum);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nx = x + dir[d][0];
            int ny = y + dir[d][1];

            if (!isRange(nx, ny) || v[nx][ny]) continue;

            v[nx][ny] = true;
            dfs(cnt + 1, sum + map[nx][ny], nx, ny, v);
            v[nx][ny] = false;
        }

    }
    
    private static void anotherShape(int i, int j) {
        //현재 i, j 위치에서 모양을 만든다
        //ㅗ
        if(isRange(i-1, j+2)){
            res = Math.max(res, map[i][j] + map[i][j+1] + map[i-1][j+1] + map[i][j+2]);
        }
        //ㅜ
        if(isRange(i+1, j+2)){
            res = Math.max(res, map[i][j] + map[i][j+1] + map[i+1][j+1] + map[i][j+2]);
        }
        //ㅏ
        if(isRange(i+2, j+1)){
            res = Math.max(res, map[i][j] + map[i+1][j] + map[i+1][j+1] + map[i+2][j]);
        }
        //ㅓ
        if(isRange(i+2, j-1)){
            res = Math.max(res, map[i][j] + map[i+1][j] + map[i+1][j-1] + map[i+2][j]);
        }
    }

    private static boolean isRange(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= M) return false;
        return true;
    }
}
