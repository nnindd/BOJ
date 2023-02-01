import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, mone, zero, one;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        mone = zero = one = 0;

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }//end input

        recur(0, 0, N);
        System.out.printf("%d\n%d\n%d", mone, zero, one);

    }

    private static void recur(int row, int col, int n) {
        //범위값 확인
        if (checkRange(row, col, n)) {
            //다 같은 수라면 해당 값++
            if (map[row][col] == -1) mone++;
            else if (map[row][col] == 0) zero++;
            else one++;
        } else {
            //다른 영역이라면 9개로 나눔
            int range = n / 3;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    //0, 0, 9라면
                    //(0,0) (0,3) (0,6)
                    //(3,0) (3,3) (3,6)
                    //(6,0) (6,3) (6,6)이 실행
                    recur(row + range * i, col + range * j, range);
                }
            }
        }

    }

    private static boolean checkRange(int row, int col, int n) { //영역이 같은 값인지 확인
        int prev = map[row][col];
        for (int i = row; i < row + n; i++) {
            for (int j = col; j < col + n; j++) {
                if (map[i][j] != prev) //같은 값이 아니라면 false
                    return false;
            }
        }
        return true;
    }
}
