package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 2121.05.05(재)
 * Level : BaekJoon Sliver 3
 * URL : https://www.acmicpc.net/problem/11659
 * Select1 : 0 ~ i인덱스의 값을 저장해놓는 누적합을 다 구해놓고 누적합으로 구간합을 구함
 * Thinking)
 *      - 누적합 sum[j] = sum[j - 1] + arr[j]
 *      - [i, j]의 구간합 = sum[j] - sum[i = 1]
 * Method : 누적합
 * Result : 이 방법은 누적합을 구하는데 O(N) + 구간합을 구하는데 O(1)의 시간복잡도를 가짐
 * Plus : arr의 값이 계속 바뀌는 경우는 세그먼트 트리를 쓰는 게 더 좋다구 한대(세그먼트 트리로도 누적합 구할 수 있음)
 */


public class BJ11659_구간합구하기4 {
    static int N, M;
    static int[] sumArr;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sumArr = new int[N + 1];
        st = new StringTokenizer(in.readLine(), " ");
        for(int i = 1 ; i <= N ; i++) {
            sumArr[i] = sumArr[i - 1] + Integer.parseInt(st.nextToken());
        }

        for(int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int le = Integer.parseInt(st.nextToken());
            int ri = Integer.parseInt(st.nextToken());
            sb.append(sumArr[ri] - sumArr[le - 1] + "\n");
        }

        System.out.println(sb.toString());
    }
}
