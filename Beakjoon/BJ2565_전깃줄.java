package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * Date : 2022.03.06
 * Level : BaekJoon Gold 5
 * Difficulty : 중상
 * Why : LIS 방법 말고 딴걸로 풀다가 틀려서 계속 못풀고 있던 문제
 * URL : https://www.acmicpc.net/problem/2565
 * Select1 : 겹치는 애들 개수 세는걸로 했다가 탈락
 * Thinking : 오른쪽 수에서 최장 증가인 애들이 남는 전깃줄!
 * Method : LIS(DP)
 * Error1 : 방법이 틀렸었음
 * Result : 거의 답 보고 풀어서.. 이걸 어떻게 LIS로 푸나 했는데 이렇게구나
 */
public class BJ2565_전깃줄 {
    static int N, ans;
    static int[] dp;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        dp = new int[N];
        arr = new int[N][2];
        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);        // 왼쪽 수를 기준으로 오름차순 정렬

        // LIS 알고리즘 적용(DP)
        for(int i = 0 ; i < N ; i++) {
            int now = arr[i][1];
            dp[i] = 1;
            for(int j = 0 ; j < i ; j++) {
                if(arr[j][1] < now) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }

        System.out.println(N - ans);
    }
}
