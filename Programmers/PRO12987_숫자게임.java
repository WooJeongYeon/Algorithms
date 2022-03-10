package programmers;

import java.util.Arrays;

// 입력이 10만일 때 이분탐색, 정렬정도인가..?
// 쉽게 풀었다. A 순서만 나오면 맞춰서 내면 되니까 -> 정렬시켜서 비교
public class PRO12987_숫자게임 {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);

        int aIdx = 0, bIdx = 0;
        while(true) {
            if(aIdx == A.length || bIdx == B.length) break;
            if(A[aIdx] < B[bIdx]) {
                aIdx++;
                answer++;
            }
            bIdx++;
        }

        return answer;
    }
}
