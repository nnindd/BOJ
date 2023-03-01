import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] arr;
    static LinkedHashSet<String> set = new LinkedHashSet<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        arr = new int[N];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tokens.nextToken());
        }

        Arrays.sort(arr);
        comb(0, 0, "");
        for(String str : set){
            sb.append(str).append("\n");
        }
        System.out.println(sb);

    }

    private static void comb(int cnt, int start, String res) {
        if(cnt==M){
            if(set.contains(res)) return;
            set.add(res);
            return;
        }

        for (int i = start; i < N; i++) {
            comb(cnt+1, i,res+arr[i]+" ");
        }
    }
}
