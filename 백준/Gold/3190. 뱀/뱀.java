import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int N, map[][], time[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;

        N = Integer.parseInt(br.readLine()); //맵 크기
        map = new int[N][N];
        time = new int[10001];

        int K = Integer.parseInt(br.readLine()); //사과의 개수
        int x, y;
        for (int i = 0; i < K; i++) {
            tokens = new StringTokenizer(br.readLine());
            x = Integer.parseInt(tokens.nextToken()) - 1;
            y = Integer.parseInt(tokens.nextToken()) - 1;
            map[x][y] = 1;
        }

        K = Integer.parseInt(br.readLine()); //방향 정보
        char w;
        for (int i = 0; i < K; i++) {
            tokens = new StringTokenizer(br.readLine());
            x = Integer.parseInt(tokens.nextToken());
            w = tokens.nextToken().charAt(0);

            time[x] = w == 'D' ? 1 : -1; //왼쪽 -1, 오른쪽 1 저장
        }

        System.out.println(play());

    }

    private static int play() {
        int res = 0; //게임 진행 시간
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; //오른쪽으로 시계방향
        int d = 0; //오른쪽
        ArrayDeque<int[]> pos = new ArrayDeque<>(); //뱀의 모든 위치 저장
        pos.offer(new int[]{0, 0}); //머리
        //자기 자신 표시
        map[0][0] = 999;

        int nx, ny;
        while (true) {
            res++;

            int[] cur = pos.peek();

            //1. 머리 늘리기
            nx = cur[0] + dir[d][0];
            ny = cur[1] + dir[d][1];

            //2. 벽인지, 자기자신인지 확인
            if (!isRange(nx, ny) || map[nx][ny] == 999) break;

            //3. 사과 유무 확인

            if (map[nx][ny] == 0) { //사과를 안먹는 경우
                int[] tail = pos.pollLast();
                map[tail[0]][tail[1]] = 0;
            }
            map[nx][ny] = 999;
            pos.offerFirst(new int[]{nx, ny});
            
            //4. 초마다 방향 전환이 있는지 확인 후 방향을 바꿔줌
            if (time[res] == -1) {
                d = (d + 3) % 4; //빼기로 진행하면 마이너스값이 나오기 때문에 3을 더해서 모듈러 연산
            } else if (time[res] == 1) {
                d = (d + 1) % 4;
            }
        }

        return res;
    }

    private static boolean isRange(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= N) return false;
        return true;
    }
}
