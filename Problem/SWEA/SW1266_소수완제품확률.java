import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
- 독립시행확률
- Pr = mCr(p^r)(g^n-r)
    - 아래의 P(A)는 이 경우들을 다 더한 것
- P(AUB) = P(A) + P(B) - P(A교집합B) = P(A) + P(B) - P(A) * P(B)
- A와 B가 독립사건일 때
- P(A교집합B) = P(A) * P(B)
- nCr의 경우를 구하는 거면 그냥 계산하면 되네.. 나 왜 부분집합썼지ㅋㅋㅋ
 */
public class SW1266_소수완제품확률 {
    static int TC, ans;
    static double pA, pB, resultA, resultB;
    static int[] primeArr = {2, 3, 5, 7, 11, 13, 17};
    static long[] combArr;
    static final int N = 18;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        TC = Integer.parseInt(in.readLine());
        combArr = new long[N + 1];
        combArr[0] = 1;
        for(int i = 1 ; i <= N ; i++) {
            combArr[i] = combArr[i - 1] * (N - i + 1) / i;
        }
        System.out.println();
        for(int tc = 1 ; tc <= TC ; tc++) {
            ans = 0;
            st = new StringTokenizer(in.readLine());
            pA = Integer.parseInt(st.nextToken()) / 100.0;
            pB = Integer.parseInt(st.nextToken()) / 100.0;
            resultA = 0.0;
            resultB =  0.0;
            for(int i = 0 ; i < primeArr.length ; i++) {
                double nowA = 0.0, nowB = 0.0;
                nowA = combArr[primeArr[i]] * Math.pow(pA, primeArr[i]) * Math.pow(1 - pA, N - primeArr[i]);
                nowB = combArr[primeArr[i]] * Math.pow(pB, primeArr[i]) * Math.pow(1 - pB, N - primeArr[i]);
                resultA += nowA;
                resultB += nowB;
            }

            sb.append("#").append(tc).append(" ").append(String.format("%.6f", resultA + resultB - resultA * resultB)).append("\n");
        }

        System.out.println(sb);
    }
}
