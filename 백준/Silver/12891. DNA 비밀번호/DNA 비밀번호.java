import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] count; // ACGT 카운트할 배열
	static int[] least = new int[4]; // ACGT 최소개수 저장 배열

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(br.readLine());
		
		int S = Integer.parseInt(tokens.nextToken()); // 문자열 길이
		int P = Integer.parseInt(tokens.nextToken()); // 비밀번호로 사용할 문자열 길이
		
		String str = br.readLine(); // 문자열 저장
		
		int res = 0; // 결과값
		
		tokens = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {//최소로 필요한 비밀번호 개수
			least[i] = Integer.parseInt(tokens.nextToken());
		}
		
		// 문자열을 p만큼 읽으면서 count 배열에 넣는다
		// p만큼 다 읽으면 카운트랑 리스트 비교한다
		// 전부 해당값이 맞으면 결과값 ++
		// 해당값이 안 맞으면 결과값 0
		// 그 다음 문자열 진행..
		
		count = new int[4];
		
		int times = S - P + 1;	//문자열 확인하는 횟수
		
		for (int k = 0; k < times; k++) {
			// 문자열을 p만큼 읽으면서 count 배열에 넣는다
			// 0번째 인덱스라면 전부 저장함
			if (k == 0) {
				for (int i = 0; i < P; i++) {
					switch (str.charAt(i)) {
					case 'A':
						count[0]++;
						break;
					case 'C':
						count[1]++;
						break;
					case 'G':
						count[2]++;
						break;
					case 'T':
						count[3]++;
						break;
					}
				}
			//0번째 인덱스가 아니라면 앞의 값을 빼주고 뒤의 값을 추가해줌
			// AGTGC 5 4 라면 A값을 배주고 C 카운트를 증가해줌
			} else {	
				switch (str.charAt(k - 1)) {
				case 'A':
					count[0]--;
					break;
				case 'C':
					count[1]--;
					break;
				case 'G':
					count[2]--;
					break;
				case 'T':
					count[3]--;
					break;
				}
				//k=1, p=4 s=5
				switch (str.charAt(P + k-1)) {
				case 'A':
					count[0]++;
					break;
				case 'C':
					count[1]++;
					break;
				case 'G':
					count[2]++;
					break;
				case 'T':
					count[3]++;
					break;
				}
			}
			// 카운트와 최소값 비교
			if(check(count, least)) { //최소값 만족하면 결과값 증가
				res++;
			}
			
		} // times end
		System.out.println(res);
		br.close();
	}// main end
	
	static boolean check(int[] cnt, int[] least) {
		for(int i=0; i<4; i++) {
			if(cnt[i] < least[i]) {
				return false;
			}
		}
		return true;
	}
}