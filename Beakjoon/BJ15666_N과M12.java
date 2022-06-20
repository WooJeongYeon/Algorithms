package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//
//

/*
 * Date : 2022.06.21
 * Level : BaekJoon Silver 2
 * Difficulty : 중하
 * URL : https://www.acmicpc.net/problem/15666
 * Thinking : 중복수열 제거는 바로 이전에 사용한 값과 다른 값 사용하도록 함
 * Method : 순열
 * Error1 : 수열이 내림차순인지 물랐넹...
 */

public class BJ15666_N과M12 {
    static int N, M;
    static int[] arr, result;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        result = new int[M];

        st = new StringTokenizer(in.readLine());
        for(int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        perm(0, 0);

        System.out.println(sb);
    }

    static void perm(int cnt, int idx) {
        if(cnt == M) {
            for(int i = 0 ; i < M ; i++) {
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        int last = -1;
        for(int i = idx ; i < N ; i++) {
            if(arr[i] != last) {        // 이전 값과 같지 않아야 순열 진행
                result[cnt] = arr[i];

                perm(cnt + 1, i);

                last = arr[i];
            }
        }
    }
}
