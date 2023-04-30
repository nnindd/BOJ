import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static boolean[] v;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(br.readLine());
		
		int[] temp = new int[4];
		for(int i=0; i<4; i++) {
			temp[i] = Integer.parseInt(tokens.nextToken()); 
		}

		int min = setMinimum(temp[0], temp[1], temp[2], temp[3]); // 가장 작은 시계수

		setClockNum(); // 있는 시계수를 다 구함. 시계수가 가능한 인덱스는 true로 설정
		int cnt = 0;
		for (int i = 1111; i <= min; i++) {
			if (v[i])
				cnt++;
		}
		System.out.println(cnt);

	}

	private static void setClockNum() {
		v = new boolean[10000]; // 9999최대시계수
		for (int a = 1; a < 10; a++) {
			for (int b = 1; b < 10; b++) {
				for (int c = 1; c < 10; c++) {
					for (int d = 1; d < 10; d++) {
						int num = setMinimum(a, b, c, d);

						if (!v[num])
							v[num] = true; // 시계수 가능
					}
				}
			}
		}
	}

	private static int setMinimum(int a, int b, int c, int d) {
		int min = Integer.MAX_VALUE;

		min = Math.min(min, a * 1000 + b * 100 + c * 10 + d);
		min = Math.min(min, b * 1000 + c * 100 + d * 10 + a);
		min = Math.min(min, c * 1000 + d * 100 + a * 10 + b);
		min = Math.min(min, d * 1000 + a * 100 + b * 10 + c);

		return min;
	}
}
