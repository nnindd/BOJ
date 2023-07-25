import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, a, b;
    static ArrayList<ArrayList<int[]>> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokens.nextToken());
        int M = Integer.parseInt(tokens.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
        }

        int max = 0;

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tokens.nextToken()) - 1;
            int to = Integer.parseInt(tokens.nextToken()) - 1;
            int w = Integer.parseInt(tokens.nextToken());

            list.get(from).add(new int[]{to, w});
            list.get(to).add(new int[]{from, w});

            max = Math.max(max, w);
        }

        tokens = new StringTokenizer(br.readLine());
        a = Integer.parseInt(tokens.nextToken()) - 1;
        b = Integer.parseInt(tokens.nextToken()) - 1;

        int left = 0; //가장 적은 중량
        int right = max; //가장 큰 중량

        //a~b의 경로가 mid인 중량을 건널 수 있는지 확인
        while (left <= right) {
            int mid = (left + right) / 2;

            if (bfs(mid)) {
                //더 큰 값을 옮길 수 있어서 left에 mid를 갱신해줌
                left = mid + 1;
            } else {
                //더 작은 값으로 옮겨야 하기 때문에 right에 mid를 갱신해줌
                right = mid - 1;
            }
        }
        System.out.println(right);
    }

    private static boolean bfs(int mid) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(a);
        boolean[] v = new boolean[N];
        v[a] = true;

        while(!q.isEmpty()){
            int cur = q.poll();

            if(cur == b) return true; //도착함

            for(int[] next : list.get(cur)){ //지금 섬에 연결된 다리 만큼
                if(v[next[0]]) continue; //방문했다면 넘어감
                if(mid > next[1]) continue; //mid보다 작은 값이라면 mid로 옮길 수 없으니 넘어감
                v[next[0]] = true;
                q.offer(next[0]);
            }
        }
        return false;
    }
}