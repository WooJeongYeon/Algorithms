package programmers;

// 더 좋게 풀 수 있네ㅠㅠㅠ XOR로...
// 다른 사람 풀이 - return Integer.toBinaryString((a-1)^(b-1)).length();

public class PRO12985_예상대진표 {
    int N, A, B, d, totalCnt;
    public int solution(int n, int a, int b)
    {
        N = n; A = a; B = b;

        divide(0, 1, N);

        for(int i = 1 ; i <= 20 ; i++) {
            if(((N >> i) & 1) == 1) {
                totalCnt = i;
                break;
            }
        }

        return totalCnt - d;
    }
    void divide(int depth, int left, int right) {
        int mid = (left + right) / 2;
        if((A <= mid && B > mid) || (A > mid && B <= mid)) {
            d = depth;
            return;
        }
        if(left <= A && A <= mid) {
            divide(depth + 1, left, mid);
        } else {
            divide(depth + 1, mid + 1, right);
        }
    }
}
