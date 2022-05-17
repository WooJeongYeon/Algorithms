package programmers;

public class PRO12911_다음큰숫자 {
    int max;
    public int solution(int n) {
        int answer = 0;
        max = Integer.MAX_VALUE;
        max = getOneCnt(n);
        for(int i = n + 1 ; i <= 1000000 ; i++) {
            if(getOneCnt(i) == max) {
                answer = i;
                break;
            }
        }
        return answer;
    }
    public int getOneCnt(int n) {
        int cnt = 0;
        while(n != 0) {
            if((n & 1) == 1)
                cnt++;
            if(cnt > max)
                return -1;
            n >>= 1;
        }
        return cnt;
    }
}
