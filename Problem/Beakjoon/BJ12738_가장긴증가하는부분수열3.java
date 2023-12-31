package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 2022.03.20
 * Level : BaekJoon Gold 2
 * URL : https://www.acmicpc.net/problem/12738
 * Method : LIS(Longest Increasing Subsequence), DP, 이분탐색
 * Error1 : 음수값 처리 -> 음수값도 저장될 수 있도록 dp에 Integer의 Min 저장
 * Error2 : 같은 수에 대한 처리
 * Result : 부분수열2와 거의 흡사 + 음수값이 들어오는것만 추가됬다.
 */

public class BJ12738_가장긴증가하는부분수열3 {
    static int N, len;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        dp = new int[N + 1];

        dp[0] = Integer.MIN_VALUE;  
        for(int i = 1 ; i <= N ; i++) {
            dp[i] = Integer.MIN_VALUE;  // 음수도 들어오므로 음수가 저장될 수 있도록 Integer의 최솟값 저장 
            int num = Integer.parseInt(st.nextToken());
            if (dp[len] < num) {        // 증가하는 값이면 뒤에 붙임
                dp[++len] = num;
            } else {                    // 아니면 자리 찾아서 저장
                int le = 1, ri = len;
                int pos = -1;
                while (le <= ri) {
                    int mid = (le + ri) / 2;
                    if (dp[mid] >= num) {       // 자기보다 큰 값 중 최솟값에 저장되도록(같은 값이 들어온 경우에도 여기에 걸려서 처리해줌)
                        pos = mid;
                        ri = mid - 1;
                    } else {
                        le = mid + 1;
                    }
                }
                dp[pos] = num;              // pos가 0이 나올일이 없음
            }

        }
        System.out.println(len);
    }
}
