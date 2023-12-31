package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 2022.02.09
 * Level : BaekJoon Sliver 3
 * URL : https://www.acmicpc.net/problem/2805
 * Method : 이분탐색
 * Error1 : 범위 지정 잘못함
 * Result : 알수록 신기한 이분탐색 문제들
 */
public class BJ2805_나무자르기 {
    static int N, M, ans;
    static int min = 0, max = 1000000000, mid;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(in.readLine(), " ");
        for(int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        while(min <= max) {
            int sum = 0;
            mid = (min + max) / 2;
            for(int i = 0 ; i < N ; i++) {
                if(arr[i] > mid) sum += arr[i] - mid;
                if(sum > M) break;
            }
            if(sum >= M) {
                ans = Math.max(ans, mid);
                if(sum == M) break;
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        System.out.println(ans);
    }
}
