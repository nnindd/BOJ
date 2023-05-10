import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokens.nextToken()); //세로
        int M = Integer.parseInt(tokens.nextToken()); //가로
        int B = Integer.parseInt(tokens.nextToken()); //인벤토리 블록 수

        int[] map = new int[N * M];
        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i*M + j] = Integer.parseInt(tokens.nextToken());
            }
        }

        int min = Integer.MAX_VALUE; //최소 시간
        int max = 0; //최대 높이

        //0~256까지 돌면서 블럭을 쌓고, 고르기 진행
        for (int height = 0; height <= 256; height++) {
            int build = 0; //블럭 쌓기
            int del = 0; //블럭 제거
            for (int i = 0; i < map.length; i++) {
                if (map[i] > height) {
                    //블럭이 현재 높이보다 높은 경우 제거
                    del += map[i] - height;
                } else if (map[i] < height) {
                    //블럭이 현재 높이보다 낮은 경우 블럭 쌓기
                    build += height - map[i];
                }
            }
            //제거한 블럭의 개수 + 인벤토리 블럭 개수가 쌓은 블럭의 수보다 같거나 큰 경우 처리 가능
            if (del + B >= build) {
                //제거 2초, 채우기 1초
                int time = del * 2 + build;
                if (min >= time) {
                    min = time;
                    max = height;
                }
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(min + " " + max);
        bw.flush();
        bw.close();
    }
}
