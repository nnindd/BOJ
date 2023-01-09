import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Main {

    static class Word implements Comparable<Word> {
        String str;

        public Word(String str) {
            this.str = str;
        }

        @Override
        public int compareTo(Word o) {
            if (this.str.length() == o.str.length()) {
                for (int i = 0; i < this.str.length(); i++) {
                    if (this.str.charAt(i) == o.str.charAt(i))
                        continue;
                    return this.str.charAt(i) - o.str.charAt(i);
                }
            }
            return this.str.length() - o.str.length();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        TreeSet<Word> set = new TreeSet<>();

        for (int i = 0; i < N; i++) {
            set.add(new Word(br.readLine()));
        }

        for(Word word : set){
            System.out.println(word.str);
        }
    }
}
