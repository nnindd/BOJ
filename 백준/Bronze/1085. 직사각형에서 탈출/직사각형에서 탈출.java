import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        //x, w-x
        //y, h-y
        int x = Integer.parseInt(tokens.nextToken());
        int y = Integer.parseInt(tokens.nextToken());
        int w = Integer.parseInt(tokens.nextToken());
        int h = Integer.parseInt(tokens.nextToken());

        System.out.println(Math.min(y, Math.min(h-y, Math.min(x, w-x))));
    }
}
