import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long S = Long.parseLong(br.readLine());

        //1부터 계속 더하면서 S가 넘지 않을때까지 반복
        long sum = 0;
        long idx = 1;
        while(true){
            sum += idx++;
            if(sum > S){
                idx--;
                break;
            }
        }
        System.out.println(idx-1);
    }
}
