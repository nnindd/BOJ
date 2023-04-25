//문제 링크 https://www.codetree.ai/training-field/search/a-train-that-goes-together/description?page=1&pageSize=20&username=

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            Integer.parseInt(tokens.nextToken());
            int speed = Integer.parseInt(tokens.nextToken());

            arr[i] = speed;
        }

        int res = 1;
        int min = arr[N - 1];

        for (int i = N - 2; i >= 0; i--) {
            if(arr[i] <= min){
                res++;
                min = arr[i];
            }
        }

        System.out.println(res);

    }
}
