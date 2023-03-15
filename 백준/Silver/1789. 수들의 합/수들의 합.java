import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long S = Long.parseLong(br.readLine());

        //1부터 계속 더하면서 S가 넘지 않을때까지 반복
        long sum = 0;
        long idx = 1;
        while(true){
            sum += idx++;
            if(sum > S){
                idx-=2; //idx++에 대해서 빼주고, sum 초과하는 것에 대해 한번 더 빼줌
                break;
            }
        }
        System.out.println(idx);
    }
}
