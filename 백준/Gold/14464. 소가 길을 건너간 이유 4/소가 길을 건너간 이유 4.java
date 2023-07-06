import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(tokens.nextToken());
        int N = Integer.parseInt(tokens.nextToken());

        int[] chick = new int[C];
        PriorityQueue<int[]> cow = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) return o1[0] - o2[0];
                return o1[1] - o2[1];
            }
        });

        for (int i = 0; i < C; i++) {
            chick[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(chick);

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            cow.offer(new int[]{Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())});
        }

        int res = 0;
        while (!cow.isEmpty()) {
            int[] now = cow.poll();

            for (int i = 0; i < C; i++) {
                if (chick[i] < 0) continue;
                if (now[0] <= chick[i] && chick[i] <= now[1]) {
                    res++;
                    chick[i] = -1;
                    break;
                }
            }
        }
        System.out.println(res);
    }
}