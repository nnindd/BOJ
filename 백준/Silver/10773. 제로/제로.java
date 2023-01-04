import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long sum = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(br.readLine());
            if(temp != 0){
                stack.push(temp);
            }else{
                stack.pop();
            }
        }
        for(int i : stack){
            sum += i;
        }

        System.out.println(sum);

    }
}
