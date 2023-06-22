import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, map[][], res[][];
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        res = new int[N][N];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }//end input

        for (int i = 0; i < N; i++) {
            v = new boolean[N];

            for (int j = 0; j < N; j++) {
                if(map[i][j] == 1 && !v[j]){
                    dfs(i, j);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(res[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

    private static void dfs(int x, int y) {
        //x, y가 1인 상태
        //y에 연결된 다른 간선 찾아야 함
        v[y] = true;
        res[x][y] = 1;

        for (int i = 0; i < N; i++) {
            if(map[y][i] == 1 && !v[i]){
                //y와 연결된 간선을 시작한 노드와 이어
                dfs(x, i);
            }
        }
    }

}
