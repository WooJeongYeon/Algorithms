package programmers;

public class PRO43165_타겟넘버 {
    int N, ans, num;
    int[] numArr;
    public int solution(int[] numbers, int target) {
        ans = 0;
        N = numbers.length;
        numArr = numbers;
        num = target;

        subset(0, 0);

        return ans;
    }
    void subset(int idx, int sum) {
        if(idx == N) {
            if(sum == num) ans++;
            return;
        }
        subset(idx + 1, sum + numArr[idx]);
        subset(idx + 1, sum - numArr[idx]);
    }
}
