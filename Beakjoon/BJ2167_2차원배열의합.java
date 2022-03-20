package baekjoon;

// 2차원 누적합 구하기 -  / 아니면 여기도 누적합도 부분합 구하는것처럼 해도 됨
// 부분합 [a1, a2] ~ [b1, b2] = sum[b1][b2] - sum[a1 - 1][b2] - sum[b1][a2 - 1] + sum[a1 - 1][a2 - 1]
// 

/*
 * Date : 2022.03.20
 * Level : BaekJoon 브론즈 1
 * Difficulty : 원래 그냥 O(N^2)로 풀 수 있는 문제
 * URL : https://www.acmicpc.net/problem/2167
 * Select1 : 2차원 누적합 구하기
 *          방법 1. 행마다 누적합 모두 -> 열마다 누적합 모두
 *          방법 2. 누적합도 구간합 구하는 것처럼 구함
 * Thinking : 부분합 [a1, a2] ~ [b1, b2] = sum[b1][b2] - sum[a1 - 1][b2] - sum[b1][a2 - 1] + sum[a1 - 1][a2 - 1]
 * Method : 구간합
 * Result : 이걸 이렇게 구하네... 생각은 했었는데 어렵다ㅠㅠ
 * Plus1 : 참고 - https://velog.io/@ohdowon064/Algorithm-2%EC%B0%A8%EC%9B%90-%EB%B0%B0%EC%97%B4-%EB%B6%80%EB%B6%84%ED%95%A9-%EB%88%84%EC%A0%81%ED%95%A9-%EA%B5%AC%ED%95%98%EA%B8%B0
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2167_2차원배열의합 {
    static int N, M, K;
    static int[][] sumArr;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sumArr = new int[N + 1][M + 1];
        for(int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            for(int j = 1 ; j <= M ; j++) {
                sumArr[i][j] = sumArr[i][j - 1] + Integer.parseInt(st.nextToken()); // 행 부분합
            }
        }
        for(int i = 1 ; i <= N ; i++) {
            for(int j = 1 ; j <= M ; j++) {
                sumArr[i][j] += sumArr[i - 1][j];       // 열 부분합
            }
        }

        K = Integer.parseInt(in.readLine());

        for(int i = 0 ; i < K ; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int a1 = Integer.parseInt(st.nextToken());
            int a2 = Integer.parseInt(st.nextToken());
            int b1 = Integer.parseInt(st.nextToken());
            int b2 = Integer.parseInt(st.nextToken());
            sb.append(sumArr[b1][b2] - sumArr[a1 - 1][b2] - sumArr[b1][a2 - 1] + sumArr[a1 - 1][a2 - 1] + "\n");
        }

        System.out.println(sb.toString());
    }
}
