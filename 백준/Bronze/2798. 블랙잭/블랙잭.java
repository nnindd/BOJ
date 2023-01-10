import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(br.readLine());

		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		int[] cards = new int[N];

		tokens = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(tokens.nextToken());
		}

		result = 0;
		comb(0, 0, 0, cards);
		
		System.out.println(result);

	}

	private static void comb(int cnt, int start, int sum, int[] cards) {
		if (sum > M)
			return;
		
		if (cnt == 3) {
			if (sum <= M) {
				result = Math.max(result, sum);
			}
			return;
		}

		for (int i = start; i < N; i++) {
			comb(cnt + 1, i + 1, sum + cards[i], cards);
		}
	}

}
