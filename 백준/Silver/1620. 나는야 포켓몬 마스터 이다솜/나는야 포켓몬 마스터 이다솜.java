import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokens.nextToken());
        int M = Integer.parseInt(tokens.nextToken());

        //문자 -> 숫자 : 맵
        //숫자 -> 문자 : 배열
        HashMap<String, Integer> map = new HashMap<>();
        String[] arr = new String[N+1];
        
        for (int i = 1; i <= N; i++){
            String str = br.readLine();
            map.put(str, i);
            arr[i] = str;
        }//end input

        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            //숫자인 경우
            if(str.charAt(0) >= '1' && str.charAt(0) <= '9'){
                //배열에서 인덱스 접근
                sb.append(arr[Integer.parseInt(str)]).append("\n");
            }
            //문자인 경우
            if(str.charAt(0) >= 'A' && str.charAt(0) <= 'Z'){
                sb.append(map.get(str)).append("\n");
            }
        }
        System.out.println(sb);
    }
}
