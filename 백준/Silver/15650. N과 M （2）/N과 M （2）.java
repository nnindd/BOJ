import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(br.readLine());
		sb = new StringBuilder();

		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		combination(0, 1, new int[M]);
		System.out.println(sb);

	}

	private static void combination(int cnt, int start,  int[] s) {
		if (cnt == M) {
			for (int i : s) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = start; i <= N; i++) {
			s[cnt] = i;
			combination(cnt + 1, i+1, s);
		}
	}

}
