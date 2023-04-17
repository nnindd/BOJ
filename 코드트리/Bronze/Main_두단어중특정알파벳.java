// 문제 링크 https://www.codetree.ai/training-field/search/specific-alphabet-of-two-words/submissions?page=3&pageSize=20&username=
import java.io.*;
import java.util.StringTokenizer;

public class Main_두단어중특정알파벳 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        int[] res = new int[26];
        int[] cnt1, cnt2;

        String left, right;

        for (int i = 0; i < T; i++) {
            StringTokenizer tokens = new StringTokenizer(br.readLine());
            left = tokens.nextToken();
            right = tokens.nextToken();

            cnt1 = new int[26];
            cnt2 = new int[26];

            for (int j = 0; j < left.length(); j++) {
                cnt1[left.charAt(j)-'a']++;
            }
            for (int j = 0; j < right.length(); j++) {
                cnt2[right.charAt(j)-'a']++;
            }

            for (int j = 0; j < 26; j++) {
                res[j] += Math.max(cnt1[j], cnt2[j]);
            }

        }

        for (int i = 0; i < 26; i++) {
            bw.write(res[i]+"\n");
        }
        bw.flush();
        bw.close();

    }
}
