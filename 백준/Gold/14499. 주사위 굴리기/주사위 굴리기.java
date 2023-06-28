import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, map[][], ju[];
    static int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; //동서북남
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        map = new int[N][M];

        int x = Integer.parseInt(tokens.nextToken());
        int y = Integer.parseInt(tokens.nextToken());

        int K = Integer.parseInt(tokens.nextToken());

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }

        //주사위 설정
        ju = new int[7];

        tokens = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        
        for (int i = 0; i < K; i++) {
            int[] now = roll(x, y, Integer.parseInt(tokens.nextToken()) - 1, sb);
            if(now == null) continue;
            x = now[0];
            y = now[1];
        }

        System.out.print(sb);

    }//end main

    private static int[] roll(int x, int y, int d, StringBuilder sb) {
        //방향대로 다음 위치 확인
        x += dir[d][0];
        y += dir[d][1];

        if (!isRange(x, y)) return null;

        changeJu(d);

        if(map[x][y] == 0){
            map[x][y] = ju[6];
        }else{
            ju[6] = map[x][y];
            map[x][y] = 0;
        }

        sb.append(ju[1]).append("\n"); //가장 윗면의 값 출력

        return new int[]{x, y};

    }

    private static boolean isRange(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= M) return false;
        return true;
    }

    private static void changeJu(int d) {
        //굴리는 방향에 따라서 위치가 변함
        //기본    1 2 3 4 5 6
        //오른    4 2 1 6 5 3
        //왼쪽    3 2 6 1 5 4
        //위쪽    5 1 3 4 6 2
        //남쪽    2 6 3 4 1 5

        int[] t = ju.clone();

        if (d == 0) { //동
            ju[1] = t[4];
            ju[3] = t[1];
            ju[4] = t[6];
            ju[6] = t[3];
        } else if (d == 1) { //서
            ju[1] = t[3];
            ju[3] = t[6];
            ju[4] = t[1];
            ju[6] = t[4];
        } else if (d == 2) { //북
            ju[1] = t[5];
            ju[2] = t[1];
            ju[5] = t[6];
            ju[6] = t[2];
        } else { //남
            ju[1] = t[2];
            ju[2] = t[6];
            ju[5] = t[1];
            ju[6] = t[5];
        }
    }
    
}
