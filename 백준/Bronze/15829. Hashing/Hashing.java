import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		
		long M = 1234567891;
		
		long result = 0;
		long mul = 1;
		
		for(int i=0; i<N; i++) {
			result += (str.charAt(i)-'a'+1) * mul % M;
			
			mul = mul * 31 % M;
		}
		System.out.println(result % M);
		
	}
}
