import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		StringTokenizer tokens;
		int H, W, N;

		for (int i = 0; i < T; i++) {
			tokens = new StringTokenizer(br.readLine());
			H = Integer.parseInt(tokens.nextToken());
			W = Integer.parseInt(tokens.nextToken());
			N = Integer.parseInt(tokens.nextToken());
			
			int yy = N % H;
			int xx = N / H + 1; //0호수 제외
			if(yy==0) {
				yy = H; //가장 윗층
				xx -= 1;
			}
			sb.append(yy);
			
			if(xx < 10) sb.append(0).append(xx);
			else sb.append(xx);
			
			sb.append("\n");
		}
		System.out.println(sb);
	}
}