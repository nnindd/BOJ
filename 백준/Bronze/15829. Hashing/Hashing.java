import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		
		int r = 31;
		long M = 1234567891;
		
		long result = 0;
		
		for(int i=0; i<str.length(); i++) {
			result += (str.charAt(i) - 'a' + 1) * Math.pow(r, i) % M;
		}
		System.out.println(result % M);
		
	}
}