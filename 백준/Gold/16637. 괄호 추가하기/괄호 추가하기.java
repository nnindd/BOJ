import java.util.Scanner;

//보충 풀이

public class Main {

	static int N;
	static char[] map;
	static int res = Integer.MIN_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = sc.next().toCharArray();

		dfs(2, map[0] - '0');
		System.out.println(res);
	}

	private static void dfs(int idx, int sum) {
		// 종료조건
		if(idx>=N) {
			res = Math.max(res, sum);
			return;
		}
		// 괄호 없이 재귀호출
		// 1+2-3-4*5
		dfs(idx + 2, calc(sum, map[idx] - '0', map[idx - 1]));

		// 괄호 추가해서 재귀 호출
		// sum+(2-3)-4*5
		if (idx + 2 < N) {
			int ta = calc(map[idx] - '0', map[idx + 2] - '0', map[idx + 1]);
			dfs(idx + 4, calc(sum, ta, map[idx - 1]));
		}
	}

	private static int calc(int a, int b, char op) {
		if (op == '+') {
			return a + b;
		} else if (op == '-') {
			return a - b;
		} else if (op == '*') {
			return a * b;
		}
		return 0;
	}
}
