package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 2022.03.06
 * Level : BaekJoon Gold 2
 * Difficulty : 상
 * Why : 방법을 몰라서 찾아봄
 * URL : https://www.acmicpc.net/problem/12015
 * Thinking : 배열 칸에 해당 인덱스 길이인 최장 증가 수열의 마지막 최솟값을 저장!(길이 인덱스에 마지막 최솟값 저장)
 *          - 기존방법 - 배열 칸에 해당 배열 값을 포함했을 때 최장 증가 수열의 길이를 저장함(값 인덱스에 최장 길이 저장)
 * Method : LIS(이분탐색)
 * Help : 예전에 배운거더라구...? 복습해야하나ㅜㅜㅜ
 * Result : DP에 뭘 저장할지 생각하는게 어렵다ㅠ
 * Plus1 : 이분탐색 메서드 사용해도 됨
 */
public class BJ12015_가장긴증가하는부분수열2 {
    static int N, endIdx;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());
        dp = new int[N + 1];
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        for(int i = 0 ; i < N ; i++) {
            int now = Integer.parseInt(st.nextToken());
            if(dp[endIdx] < now) { // 마지막 값보다 크면 새로 넣고(최장증가수열 하나 늘어남)
                dp[++endIdx] = now;
            } else if(dp[endIdx] > now){    // 마지막 값보다 작으면 넣을 자리 찾기 - 자기보다 큰 최솟값 자리에 넣음)
                setDP(now);
            }
        }

        System.out.println(endIdx);
    }
    static void setDP(int now) {        // 정렬되어 있으니까 이분탐색으로 자리 찾아서 넣음
        int left = 1, right = endIdx;
        int minValue = Integer.MAX_VALUE;
        int minIdx = -1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(dp[mid] < now) {
                left = mid + 1;
            } else if(dp[mid] > now) {
                if(dp[mid] < minValue) {
                    minValue = dp[mid];
                    minIdx = mid;
                }
                right = mid - 1;
            } else return;  // 같은 값이면 넣을 필요 X
        }
        dp[minIdx] = now;
    }
}
