import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] arr;
    static LinkedHashSet<String> set = new LinkedHashSet<>();

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
        comb(0, new boolean[N], "");
        for(String str : set){
            System.out.println(str);
        }

    }

    private static void comb(int cnt, boolean[] v, String res) {
        if(cnt==M){
            if(set.contains(res)) return;
            set.add(res);
            return;
        }

        for (int i = 0; i < N; i++) {
            if(v[i]) continue;
            v[i] = true;
            comb(cnt+1, v,res+arr[i]+" ");
            v[i] = false;
        }
    }
}
