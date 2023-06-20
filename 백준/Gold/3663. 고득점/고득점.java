import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {// test case

			String word = br.readLine();

			int result = 0;

			// 알파벳 최소값
			for (int i = 0; i < word.length(); i++) {
				int c = word.charAt(i) - 'A'; // 한 글자를 숫자로 변환
				result += Math.min(Math.abs(c - 0), Math.abs(c - 26)); // 알파벳 바꾸기
			}

			// 자리이동 최소값
			int shift = word.length() - 1; // 오른쪽으로 이동하는 기본 이동 값
			for (int i = 0; i < word.length(); i++) {
				int x = i + 1; // A 시작 위치 기억
				while (x < word.length() && word.charAt(x) == 'A') { // A가 연속해서 나오는 위치 저장
					x++;
				} // 연속된 A 끝남. x는 연속된 A후 다음 알파벳의 인덱스가 됨

				// 1) 오른쪽으로 이동했다가 처음으로 돌아가서 끝에서부터 A가 아닌곳까지 오는 방법
				// BCDAAANN => BCDCBNN 순서로 이동
				int left = i + i + word.length() - x;

				// 2) 0에서 출발해서 끝에서 좌측으로 읽었다가 다시 0으로 돌아와서 오른쪽으로 이동
				// BCDAAANN => BNNNNBCD 순서로 이동
				int right = (word.length() - x) * 2 + i;

				//1,2번 방법에서의 최소값을 구하고, 기본 우측 이동과 비교한 최소값 구함
				shift = Math.min(shift, Math.min(left, right));

			} // end loop

			result+= shift;
			sb.append(result).append("\n");
			
		} // end test case
		
		System.out.println(sb);
        
	}//end main
}//end class
