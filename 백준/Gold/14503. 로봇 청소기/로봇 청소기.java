import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, res, map[][];
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; //북 동 남 서

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        map = new int[N][M];

        tokens = new StringTokenizer(br.readLine());
        //처음 위치와 방향
        int r = Integer.parseInt(tokens.nextToken());
        int c = Integer.parseInt(tokens.nextToken());
        int d = Integer.parseInt(tokens.nextToken());

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                //0 청소되지 않은 칸, 1 벽
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }

        res = 0;

        while (r != -1 && c != -1 && d != -1) {
            int[] temp = move(r, c, d);
            r = temp[0];
            c = temp[1];
            d = temp[2];
        }

        System.out.println(res);
    }

    private static int[] move(int r, int c, int d) {
        //현재 칸 확인
        if (checkNowArea(r, c)) {
            //청소되지 않았다면 청소
            res++;
            map[r][c] = -1; //청소되었다고 표시
        }

        //주변 4칸 확인
        if (checkAroundArea(r, c)) {//다 청소된 경우
            //후진 가능한지 확인
            int[] temp = isBack(r, c, d);

            //후진 가능하다면 후진, 후진 불가능이면 작동 멈춤
            if (r == temp[0] && c == temp[1] && d == temp[2]) {
                //후진 불가능한 경우 멈춤
                return new int[]{-1, -1, -1};
            }
            //후진 가능한 경우
            return new int[]{temp[0], temp[1], temp[2]};

        } else {//청소되지 않은 칸이 있는 경우
            //방향 반시계로 90도 회전 -> 3 더하고 모듈러
            d = (d + 3) % 4;

            //앞쪽 칸이 청소되지 않은 빈칸이면 전진하고 다시 움직임
            int nx = r + dir[d][0];
            int ny = c + dir[d][1];
            if (isRange(nx, ny) && map[nx][ny] == 0) {//청소되지 않은 칸인 경우
                return new int[]{nx, ny, d}; //한칸 전진
            }
            //그냥 방향만 바꾸고 제자리
            return new int[]{r, c, d};

        }
    }

    private static int[] isBack(int r, int c, int d) {
        //현재 위치에서 후진 할 수 있는지 확인
        //현재 방향의 반대 방향 선택. 2를 더하고 모듈러
        int nd = (d + 2) % 4;
        int nx = r + dir[nd][0];
        int ny = c + dir[nd][1];

        //뒤로 갈 수 있고, 벽이 아닌 경우
        if (isRange(nx, ny) && map[nx][ny] != 1) return new int[]{nx, ny, d};
        return new int[]{r, c, d};

    }

    private static boolean checkAroundArea(int r, int c) {
        //주변에 0이 없다면 true
        for (int d = 0; d < 4; d++) {
            int nx = r + dir[d][0];
            int ny = c + dir[d][1];

            if (isRange(nx, ny) && map[nx][ny] == 0)
                return false;
        }
        return true;
    }

    private static boolean isRange(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= M) return false;
        return true;
    }

    private static boolean checkNowArea(int r, int c) {
        if (map[r][c] == 0) return true; //청소되지 않은 칸이라면 true
        return false;
    }
}
