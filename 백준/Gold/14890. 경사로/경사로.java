import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, L, res, map[][], map2[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokens.nextToken());
        L = Integer.parseInt(tokens.nextToken());
        map = new int[N][N];
        map2 = new int[N][N]; //i,j위치 반전시킨 맵

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
                map2[j][i] = map[i][j];
            }
        }

        res = 0;

        //기본 맵 행 검사, 회전한 맵 행 검사
        for (int i = 0; i < N; i++) {
            if (checkRow(map[i])) {
                res++;
            }
            if(checkRow(map2[i])){
                res++;
            }
        }

        System.out.println(res);
    }

    private static boolean checkRow(int[] rows) {
        //높이가 달라졌을 때 경사로를 생성할 수 있는지 확인
        int prev = rows[0];
        int cnt = 1;
        for (int i = 1; i < N; i++) {
            if (prev == rows[i]) { //높이가 같은 경우 그냥 증가
                cnt++;
                continue;
            }
            //높이가 다를 때
            if (Math.abs(prev - rows[i]) != 1) {
                //높이 차이가 1이 아니라면 경사로 만들지 못함
                return false;
            }
            //높이 차이가 1일 때
            //높아지는 경우 -> L만큼 공간을 만들 수 있는지 확인
            if (rows[i] - prev == 1) {
                if (cnt >= L) {
                    //경사로 생성
                    cnt = 1;
                    prev = rows[i];
                } else {
                    return false;
                }
            } else {
                //낮아지는 경우
                boolean flag = true;
                for (int j = i + 1; j < i + L; j++) {
                    if (!(j < N && rows[j] == rows[i])) {
                        flag = false;
                        break;
                    }
                }
                if (!flag) {
                    return false;
                } else {
                    //만들 수 있는 경우 만들고 경사로 만든 인덱스 조정하기
                    cnt = 0;
                    i = i + L - 1;
                    prev = rows[i];
                }

            }

        }
        return true;
    }
}
