import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(tokens.nextToken()); //행
        int N = Integer.parseInt(tokens.nextToken()); //열

        char[][] map = new char[M][N];
        for (int i = 0; i < M; i++) {
            map[i] = br.readLine().toCharArray();
        }

        //8 * 8로 만들 수 있는 정사각형만큼 반복
        int row = M - 8 + 1;
        int col = N - 8 + 1;
        int res = Integer.MAX_VALUE;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                res = checkArea(i, j, map, res);
            }
        }
        System.out.println(res);

    }

    private static int checkArea(int row, int col, char[][] map, int res) {
        char[] W = {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'};
        char[] B = {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'};

        //현재 row행 col열에서 시작하여 8*8의 정사각형 확인
        int wcnt = 0; //W로 시작하는 경우 색칠할 사각형 수
        int bcnt = 0; //B로 시작하는 경우 색칠할 사각형 수

        //해당 정사각형에서 색이 일치하지 않는 사각형 개수 확인
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                char now = map[row + i][col + j];
                if(i%2==0){
                    if(now!=W[j]) wcnt++;
                    if(now!=B[j]) bcnt++;
                }else{
                    if(now!=B[j]) wcnt++;
                    if(now!=W[j]) bcnt++;
                }
                if(wcnt > res && bcnt > res)
                    return res;
            }
        }
        return Math.min(res, Math.min(wcnt, bcnt));
    }
}
