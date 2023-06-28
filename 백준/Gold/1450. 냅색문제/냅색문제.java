import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int C, arr[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());

        arr = new int[N];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tokens.nextToken());
        }

        ArrayList<Long> list1 = new ArrayList<>();
        ArrayList<Long> list2 = new ArrayList<>();

        dfs(0, N / 2, 0, list1);
        dfs(N / 2, N, 0, list2);

        Collections.sort(list1);
        Collections.sort(list2);

        int cnt = 0;
        int end = list2.size() - 1;

        for (int i = 0; i < list1.size(); i++) {
            while(end >= 0 &&
                    list1.get(i) + list2.get(end) > C){
                end--;
            }
            //두개를 더한 값이 무게 안정권인 경우
            cnt += end + 1; //0번째 인덱스 설정
        }

        System.out.println(cnt);

    }

    private static void dfs(int start, int end, long sum, ArrayList<Long> list) {
        if (start == end) {
            list.add(sum);
            return;
        }
        dfs(start + 1, end, sum, list); //선택 안 하는 경우
        dfs(start + 1, end, sum + arr[start], list);
    }
}
