import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static int R, C, K, r, c;
    static int[][] map, temp;
    static ArrayList<Numbs> list;

    static class Numbs implements Comparable<Numbs>{
        int num, count;

        public Numbs(int num, int count) {
            this.num = num;
            this.count = count;
        }

        @Override
        public int compareTo(Numbs o) { //등장횟수 오름차순, 같다면 수 오름차순
            if(this.count==o.count) return this.num-o.num;
            return this.count-o.count;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(br.readLine());

        R = Integer.parseInt(tokens.nextToken()) - 1; //index 매칭
        C = Integer.parseInt(tokens.nextToken()) - 1;
        K = Integer.parseInt(tokens.nextToken());

        map = new int[3][3];
        for (int i = 0; i < 3; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }
        //입력 완료

        int times = -1;
        while (true){
            times++;

            //맵 행열 갱신
            r = map.length;
            c = map[0].length;

            //탈출조건 1 : map[r][c]==k
            if((R<r && C<c)&&map[R][C]==K){
                //검사하려는 R C가 현재 행 열의 값 내에 있어야 함
                break;
            }
            //탈출조건 2 : 100이 넘어가면 -1 출력
            if(times>=100){
                times = -1;
                break;
            }

            temp = new int[101][101]; //임시로 리스트의 값을 순서대로 저장하기 위한 배열
            if(r>=c) {
                calRow(); //열 계산
            }
            else {
                calCol(); //행 계산
            }

        }//end while

        System.out.println(times);

    }//end main

    private static void calCol() {
        int max = Integer.MIN_VALUE; //한 열당 최대 행을 갱신하기 위한 max 변수

        //한 열씩 읽으면서 계산
        for (int j = 0; j < c; j++) {
            int[] cnt = new int[101]; //한 줄씩 카운트 배열 초기화
            for (int i = 0; i < r; i++) {
                if(map[i][j]!=0){
                    cnt[map[i][j]]++; //카운트값 증가
                }
            }//end row

            //카운트값과 인덱스를 순서대로 추가
            list = new ArrayList<>();
            for (int i = 1; i < 101; i++) {
                if(cnt[i]!=0){
                    list.add(new Numbs(i, cnt[i])); //수, 등장횟수 저장
                }
            }

            //리스트 정렬
            Collections.sort(list);

            //100*100 크기의 임시 배열에 리스트 값을 저장함
            int index = 0;
            for (int i = 0; i < list.size(); i++) {
                temp[index][j] = list.get(i).num;
                temp[index+1][j] = list.get(i).count;
                index += 2; //2 step을 위해
            }
            max = Math.max(max, list.size()*2); //최대 열의 개수 갱신
        }

        if(max>100)
            max = 100; //최대 행이 100 넘으면 100로 재갱신, 이외에는 최대로

        //원래 배열의 크기를 새로 만들고 임시 배열의 값을 복사해줌
        map = new int[max][c];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = temp[i][j];
            }
        }
    }

    private static void calRow() {
        int max = Integer.MIN_VALUE; //한 행당 최대 열을 갱신하기 위한 max 변수
//        temp = new int[101][101]; //임시로 리스트의 값을 순서대로 저장하기 위한 배열

        //한 행씩 읽으면서 계산
        for (int i = 0; i < r; i++) {
            int[] cnt = new int[101]; //한 줄씩 카운트 배열 초기화
            for (int j = 0; j < c; j++) {
                if(map[i][j]!=0){
                    cnt[map[i][j]]++; //카운트값 증가
                }
            }//end col

            //카운트값과 인덱스를 순서대로 추가
            list = new ArrayList<>();
            for (int j = 1; j < 101; j++) {
                if(cnt[j]!=0){
                    list.add(new Numbs(j, cnt[j])); //수, 등장횟수 저장
                }
            }

            //리스트 정렬
            Collections.sort(list);

            //100*100 크기의 임시 배열에 리스트 값을 저장함
            int index = 0;
            for (int j = 0; j < list.size(); j++) {
                temp[i][index] = list.get(j).num;
                temp[i][index+1] = list.get(j).count;
                index += 2; //2 step을 위해
            }
            max = Math.max(max, list.size()*2); //최대 열의 개수 갱신
        }

        if(max>100)
            max = 100; //최대 열이 100 넘으면 100로 재갱신, 이외에는 최대로

        //원래 배열의 크기를 새로 만들고 임시 배열의 값을 복사해줌
        map = new int[r][max];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = temp[i][j];
            }
        }
    }
}
