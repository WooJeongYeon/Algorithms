// 계속 인덱스 나가ㅠㅠㅠ -> 한 행에서만 속할 수 있음 + 인덱스 계속 잘못씀
class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int)(right - left + 1)];
        int idx = 0;
        for(long i = left ; i <= right ; i++) {
            if((i / n) < (i % n)) answer[idx++] = (int)(i % n + 1);
            else answer[idx++] = (int)(i / n + 1);
        }
        return answer;
    }
}