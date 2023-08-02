import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, row[], res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        //한 행에서 퀸을 놓을 수 있는지 확인
        row = new int[N];
        res = 0;
        dfs(0);

        System.out.println(res);
    }

    private static void dfs(int dept) {
        if (dept == N) { //끝까지 탐색
            res++;
            return;
        }

        for (int i = 0; i < N; i++) {
            row[dept] = i; //현재 행의 i번째 열에 퀸을 놓아봄

            if (checkQueen(dept)) { //이 자리에 퀸을 놓을 수 있다면 다음 행으로 감
                dfs(dept + 1);
            }
        }
    }

    private static boolean checkQueen(int dept) {
        //[2,0,2,0]인 경우 같은 열에 놓이면 안됨
        //[2,0,1,0]인 경우 대각선에 위치해 있기 때문에 안됨 (0-1), (1-2)
        for (int i = 0; i < dept; i++) {
            if (row[dept] == row[i]) return false;
            if (Math.abs(dept - i) == Math.abs(row[dept] - row[i])) return false;
        }
        return true;
    }
}
