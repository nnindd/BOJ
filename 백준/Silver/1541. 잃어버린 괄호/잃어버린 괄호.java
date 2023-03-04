import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String temp = br.readLine();

		StringTokenizer st = new StringTokenizer(temp, "-"); //-를 기준으로 분리
		
		int result = 0;
		boolean flag = true; //최초 숫자 확인
		
		while (st.hasMoreTokens()) {
			int value = 0;
			String sub = st.nextToken();
			StringTokenizer tokens = new StringTokenizer(sub, "+");
			
			while (tokens.hasMoreTokens()) {
				value += Integer.parseInt(tokens.nextToken());
			}
			if(flag) {
				result = value;
				flag = false;
			}else {
				result -= value;
			}
		}
		
		System.out.println(result);
		
	}
	
}
