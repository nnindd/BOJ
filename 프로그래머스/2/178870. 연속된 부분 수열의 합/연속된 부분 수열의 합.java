class Solution {
    public int[] solution(int[] sequence, int k) {
        //합이 k보다 작으면 r을 증가
        //합이 k보다 크거나 같으면 l을 증가
        //l, r이 같으면 r을 증가
        
        int len = sequence.length;
        
        int left = 0, right = 0; //초기 인덱스는 0으로 설정
        int sum = sequence[0]; //초기 값 저장
        
        //결과값
        int size = 1000001;
        int start = 1000001, end = 1000001;
        
        while(right < len){
            //인덱스 저장 조건
            if(sum == k && right-left < size){
                size = right-left;
                start = left;
                end = right;
            }
            
            if(sum < k){ //합이 k보다 작은 경우 오른쪽 포인터를 증가
                right++;
                if(right < len) sum += sequence[right];
            }else{ //합이 k보다 크거나 같으면 왼쪽 포인터를 증가
                if(left < len) sum -= sequence[left];
                left++;
            }
        }
        
        return new int[]{start, end};
    }
}
//1,2,3,4,5 k=7
//l=1, r=1 sum = 1
//l=1, r=2 sum = 3
//l=1, r=3 sum = 6
//l=1, r=4 sum = 10
//l=2, r=4 sum = 9
//l=3, r=4 sum = 7 -> 인덱스 저장
//l=4, r=4 sum = 4
//l=4, r=5 sum = 9
//l=5, r=5 sum = 5
//종료

//1,1,1,2,3,4,5 k=5
//l=1, r=1 sum = 1
//l=1, r=2 sum = 2
//l=1, r=3 sum = 3
//l=1, r=4 sum = 5 -> 인덱스 저장
//l=2, r=4 sum = 4
//l=2, r=5 sum = 7
//l=3, r=5 sum = 6
//l=4, r=5 sum = 5 -> 인덱스 저장
//l=5, r=5 sum = 3
//l=5, r=6 sum = 7
//l=6, r=6 sum = 4
//l=6, r=7 sum = 9
//l=7, r=7 sum = 5 -> 인덱스 저장