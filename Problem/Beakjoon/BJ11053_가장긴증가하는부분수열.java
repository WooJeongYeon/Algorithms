package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 2022.03.06
 * Level : BaekJoon Sliver 2
 * URL : https://www.acmicpc.net/problem/11053
 * Thinking : dp를 사용해 해당 칸(dp[i])에 arr[i]보다 작은 arr값 중, dp값이 가장 큰 애 + 1을 저장!
 * Method : LIS(Longest Increasing Subsequence : 최장 증가 수열) - log(n^2)
 * Result : 2풀다가 아무리 봐도 시간초과각이라 LIS 적용해보려고 1번 풀어봤따. 오랜만에 하니까 기억이 잘 안났으ㅠ
 */
public class BJ11053_가장긴증가하는부분수열 {
    static int N, ans;
    static int[] memo, arr;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        memo = new int[N];
        arr = new int[N];
        ans = Integer.MIN_VALUE;
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        for(int i = 0 ; i < N ; i++) {
            int now = Integer.parseInt(st.nextToken());
            int max = 1;
            for(int j = 0 ; j < i ; j++) {
                if(arr[j] < now) max = Math.max(max, memo[j] + 1);
            }
            memo[i] = max;
            arr[i] = now;
            ans = Math.max(ans, memo[i]);
        }

        System.out.println(ans);
    }
}
