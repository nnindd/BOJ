import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(tokens.nextToken());
        int B = Integer.parseInt(tokens.nextToken());
        int V = Integer.parseInt(tokens.nextToken());

        int day = (V - B) / (A - B);
        if((V - B) % (A - B) != 0){
            day++;
        }
        System.out.println(day);
    }
}
