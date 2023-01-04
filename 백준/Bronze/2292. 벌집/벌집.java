import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        //2~7
        //2+6=8 8~19
        //8+6*2=20 20~37
        //20+6*3 38~...
        if(N==1){
            System.out.println(1);
        }else{
            int cnt = 0;
            int start = 2;
            while(start<=N) {
                start += (6 * cnt);
                cnt++;
            }
            System.out.println(cnt);
        }
    }
}
