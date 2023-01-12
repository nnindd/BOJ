import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Person implements Comparable<Person>{
		int age;
		int idx;
		String name;

		public Person(int age, int idx, String name) {
			this.age = age;
			this.idx = idx;
			this.name = name;
		}

		@Override
		public int compareTo(Person o) {
			if(this.age == o.age) return this.idx - o.idx; 
			return this.age - o.age;
		}

		@Override
		public String toString() {
			return age + " " + name;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer tokens;
		PriorityQueue<Person> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(br.readLine());
			pq.add(new Person(Integer.parseInt(tokens.nextToken()), i, tokens.nextToken()));
		}
		
		while(!pq.isEmpty()) {
			System.out.println(pq.poll().toString());
		}

	}

}
