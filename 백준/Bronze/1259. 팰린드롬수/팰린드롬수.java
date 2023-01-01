import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String str = br.readLine();
            if(str.equals("0"))
                break;

            boolean flag = true;
            int len = str.length();
            for(int i=0; i<len/2+1; i++){
                if(str.charAt(i) != str.charAt(len-i-1)){
                    flag = false;
                    break;
                }
            }

            System.out.println(flag ? "yes" : "no");
        }
    }
}
