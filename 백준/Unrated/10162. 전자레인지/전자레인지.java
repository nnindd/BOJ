import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int[] arr = {300, 60, 10};

        for (int i = 0; i < 3; i++) {
            sb.append(T/arr[i]).append(" ");
            T%=arr[i];
        }

        if(T != 0){
            sb.delete(0, sb.length()).append(-1);
        }
        System.out.println(sb);

    }
}
