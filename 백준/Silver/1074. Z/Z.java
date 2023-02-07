import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int num, r, c, N;
	
	static void divide(int size, int start, int end) {
		if(size == 1) return;
		//1 2
		//3 4
		if(start < size/2 && end < size/2) {
			divide(size/2, start, end); //1
		}else if(start < size/2 && end >= size/2) {
			num += size * size / 4;
			divide(size/2, start, end - size/2); //2
		}else if(start >= size/2 && end < size/2) {
			num += size * size / 4 * 2;
			divide(size/2, start-size/2, end); //3
		}else if(start >= size/2 && end >= size/2) {
			num += size * size / 4 * 3;
			divide(size/2, start - size/2, end - size/2); //4
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		int size = (int)Math.pow(2, N); //2^N
		
		//1 2
		//3 4
		divide(size, r, c);
		
		System.out.println(num);
		
	}

}
