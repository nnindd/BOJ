//문제 링크 https://www.codetree.ai/training-field/search/milk-production-competition/description?page=3&pageSize=20&username=
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_우유생산량경쟁 {

    static final String B = "Bessie";
    static final String E = "Elsie";
    static final String M = "Mildred";

    static class Cows{
        String name;
        int upDown;

        public Cows(String name, int upDown) {
            this.name = name;
            this.upDown = upDown;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens;

        int N = Integer.parseInt(br.readLine());
        Cows[] days = new Cows[101];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());

            int day = Integer.parseInt(tokens.nextToken());
            String cow = tokens.nextToken();
            int upDown = Integer.parseInt(tokens.nextToken());

            days[day] = new Cows(cow, upDown);
        }

        //가장 처음 날짜는 7갤런씩, 3마리 모두 전광판에 이름을 올림
        int res = 0;
        int[] score = new int[]{7, 7, 7};
        int[] prev = new int[]{1, 1, 1};

        for (int i = 0; i <= 100; i++) {
            if(days[i] == null) continue;

            //우유 등락 계산
            if(days[i].name.equals(B)){
                score[0] += days[i].upDown;
            } else if (days[i].name.equals(E)) {
                score[1] += days[i].upDown;
            }else {
                score[2] += days[i].upDown;
            }

            //최대값 구하기
            int max = Math.max(score[0], Math.max(score[1], score[2]));

            //전광판에 올라갈 최대값만 저장
            int[] temp = new int[3];
            for (int j = 0; j < 3; j++) { //최대값만 저장
                if(score[j] == max) temp[j] = 1;
            }

            //이전 전광판이랑 비교해서 달라졌는지 확인
            boolean flag = false;
            for (int j = 0; j < 3; j++) {
                if(prev[j] != temp[j]) flag = true;
            }
            if(flag) res++;

            //전광판 갱신
            prev = Arrays.copyOf(temp, 3);
        }
        System.out.println(res);
    }
}
