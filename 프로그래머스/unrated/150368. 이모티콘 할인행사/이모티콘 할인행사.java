class Solution {
    static int len, resEmo, resSell;
    static int[] r = {40, 30, 20, 10};
    static int e[], u[][];
    public static int[] solution(int[][] users, int[] emoticons) {
        e = emoticons;
        u = users;
        len = emoticons.length;
        resEmo = 0;
        resSell = 0;

        //이모티콘마다 할인율 10, 20, 30, 40 중 선택
        dfs(0, emoticons.length, new int[emoticons.length]);

        System.out.println(resEmo+" "+resSell);

        return new int[]{resEmo, resSell};
    }
    private static void dfs(int dept, int len, int[] s){
        //종료조건
        if(dept == len){
            calcMember(s);
            return;
        }

        //이모티콘 선택
        for (int i = 0; i < 4; i++) {
            s[dept] = r[i]; //할인율 선택
            dfs(dept+1, len, s);
        }
    }

    private static void calcMember(int[] s) {

        int emoPlus = 0;
        int totalSell = 0;

        //사용자 수만큼 for, 구매할 사람 선택
        for(int[] user : u){
            int personRate = user[0];
            int limitMoney = user[1];

            //한 사람이 지불할 가격
            int money = 0;
            //살 이모티콘 선택
            for (int i = 0; i < len; i++) {
                if(s[i] >= personRate){ //일정 비율 이상 할인이라면
                    //살 돈 계산
                    money += e[i] * (100-s[i]) / 100;
                }
            }//살 이모티콘 계산 끝

            //이모티콘 플러스 가입 여부 확인
            if(money >= limitMoney){
                //이모티콘 플러스 가입
                emoPlus++;
            }else{
                totalSell += money; //판매 총액 갱신
            }
        }

        //최종 이모티콘 플러스 가입자, 판매총액 비교
        if(resEmo < emoPlus){
            resEmo = emoPlus;
            resSell = totalSell;
        }else if(resEmo == emoPlus && resSell < totalSell){
            resSell = totalSell;
        }
    }
}