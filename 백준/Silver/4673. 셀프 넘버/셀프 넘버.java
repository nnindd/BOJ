import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int sum;
        //9999 -> 9999+9+9+9+9 = 10035
        int[] arr = new int[11000];
        for (int i = 1; i <= 10000; i++) {
            //1~10000까지 숫자를 다 만듦
            sum = 0;
            String str = Integer.toString(i);
            for (int j = 0; j < str.length(); j++) {
                sum += str.charAt(j) - '0';
            }
            sum += i;
            arr[sum]++;
        }
        for (int i = 1; i <= 10000; i++) {
            if(arr[i] == 0){
                sb.append(i).append("\n");
            }
        }
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
