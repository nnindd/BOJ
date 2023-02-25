import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] arr, s;
	static boolean[] v;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(br.readLine());
		sb = new StringBuilder();

		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		arr = new int[N];
		tokens = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(tokens.nextToken());
		}
		Arrays.sort(arr);

		s = new int[M];
		v = new boolean[N];
		perm(0);
		System.out.println(sb);

	}

	private static void perm(int cnt) {
		if (cnt == M) {
			for (int i : s) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 0; i < N; i++) {
			if (v[i])
				continue;
			v[i] = true;
			s[cnt] = arr[i];
			perm(cnt + 1);
			v[i] = false;
		}
	}

}
