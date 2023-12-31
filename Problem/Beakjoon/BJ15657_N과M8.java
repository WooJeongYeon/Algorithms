package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 중복 조합
public class BJ15657_N과M8 {
    static int N, M;
    static int[] num;
    static int[] result;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        num = new int[N];
        result = new int[M];
        st = new StringTokenizer(in.readLine(), " ");
        for(int i = 0 ; i < N ; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(num);
        comb(0, 0);

        System.out.println(sb.toString());
    }
    static void comb(int idx, int cnt) {
        if(cnt == M) {
            for(int i = 0 ; i < M ; i++) {
                sb.append(result[i] + " ");
            }
            sb.append("\n");
            return;
        }
        for(int i = idx ; i < N ; i++) {
            result[cnt] = num[i];
            comb(i, cnt + 1);
        }
    }
}
