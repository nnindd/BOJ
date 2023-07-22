import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, white, blue;
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new boolean[N][N];

        white = 0;
        blue = 0;

        StringTokenizer tokens;
        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                if (Integer.parseInt(tokens.nextToken()) == 0) {
                    map[i][j] = false; //하얀색
                } else {
                    map[i][j] = true; //파란색
                }
            }
        }

        solve(0, N, 0, N);
        StringBuilder sb = new StringBuilder();
        sb.append(white).append("\n").append(blue);
        System.out.println(sb);
    }

    private static void solve(int r1, int r2, int c1, int c2) {
        if (r1 > r2) return;

        if (!countPaper(r1, r2, c1, c2, map[r1][c1])) { //섞인 경우
            //4개의 색종이로 나눠서 진행
            int mid = (r2 - r1) / 2;
            solve(r1, r1 + mid, c1, c1 + mid);
            solve(r1, r1 + mid, c1 + mid, c2);
            solve(r1 + mid, r2, c1, c1 + mid);
            solve(r1 + mid, r2, c1 + mid, c2);
        } else { //하나의 색종이인 경우
            if (map[r1][c1]) {
                blue++;
            } else {
                white++;
            }
        }
    }

    private static boolean countPaper(int r1, int r2, int c1, int c2, boolean start) {
        for (int i = r1; i < r2; i++) {
            for (int j = c1; j < c2; j++) {
                if (map[i][j] != start) { //해당하는 색종이만 탐색
                    return false;
                }
            }
        }
        return true;
    }
}
