// 문제 링크 https://www.codetree.ai/training-field/search/specific-alphabet-of-two-words/description?page=1&pageSize=20&username=hjle2&tier=1%2C5&tags=Greedy&statuses=&name=

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_수의곱셈 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokens = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(tokens.nextToken());
        int b = Integer.parseInt(tokens.nextToken());
        int c = Integer.parseInt(tokens.nextToken());

        ArrayList<Integer> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(a * b);
        list.add(a * c);
        list.add(b * c);
        list.add(a * b * c);

        //큰 수 정렬
        Collections.sort(list, Collections.reverseOrder());

        //홀수 정렬
        Collections.sort(list, ((x, y) ->{
            if(x%2==1 && y%2==0){
                return x-y;
            }
            return y-x;
        }));

        System.out.println(list.get(0));

    }

}
