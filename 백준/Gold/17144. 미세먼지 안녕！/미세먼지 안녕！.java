import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C, map[][], airRow;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        R = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());
        int T = Integer.parseInt(tokens.nextToken());

        airRow = -1; //공기청정기 위치 초기화

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
                if (map[i][j] == -1) {
                    airRow = i;
                }
            }
        }
        airRow--; //공기청정기의 가장 위의 위치로

        while (T-- > 0) {
            play();
        }

        System.out.println(countTotalDust());
    }

    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //미세먼지 확산 방향


    private static void play() {
        //1. 미세먼지 확산
        spredDust();

        //2. 공기청정기 작동
        airGo();
    }

    private static void spredDust() { //미세먼지 확산
        //미세먼지가 있는 곳 확인
        //임시맵 생성
        int[][] temp = new int[R][C];
        //공기청정기 위치 생성
        temp[airRow][0] = -1;
        temp[airRow + 1][0] = -1;

        //4방 확인해서 확산할 곳 카운트해서 복사맵에 더해서 넣어줌
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 0 || map[i][j] == -1) continue;

                int cnt = 0; //확산할 곳
                for (int d = 0; d < 4; d++) {
                    int nx = i + dir[d][0];
                    int ny = j + dir[d][1];

                    if (!isRange(nx, ny) || map[nx][ny] == -1) continue;
                    cnt++;
                    temp[nx][ny] += map[i][j] / 5;
                }
                int remain = map[i][j] - (map[i][j] / 5 * cnt);
                temp[i][j] += remain;
            }
        }

        map = temp.clone(); //맵 복제
    }

    private static boolean isRange(int x, int y) {
        if (x < 0 || y < 0 || x >= R || y >= C) return false;
        return true;
    }

    private static void airGo() { //공기청정기 가동
        int[][] antiClockWise = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}}; //반시계방향
        int[][] clockWise = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; //시계방향

        rotateAir(airRow, 0, antiClockWise);
        rotateAir(airRow + 1, 0, clockWise);
    }

    private static void rotateAir(int row, int col, int[][] direction) {
        int idx = 0;
        //초기 위치
        int x = row;
        int y = col;
        int prev = 0; //초기 값
        while (true) {
            x += direction[idx][0];
            y += direction[idx][1];

            if (!isRange(x, y)) {//범위를 벗어나면 다음 방향으로 전환
                x -= direction[idx][0]; //위치 복귀
                y -= direction[idx][1];
                idx++;
                continue;
            }

            if (x == row && y == col) { //공기청정기를 만남
                break;
            }

            int temp = map[x][y];
            //지금 위치에 이전의 값을 넣음
            map[x][y] = prev;
            //자신의 값을 이전 값으로 갱신
            prev = temp;
        }
    }


    private static int countTotalDust() { //미세먼지의 수를 셈
        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == -1) continue;
                sum += map[i][j];
            }
        }
        return sum;
    }
}
