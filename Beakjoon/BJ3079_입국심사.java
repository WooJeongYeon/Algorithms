package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ3079_입국심사 {
    static int N, M;
    static long ans, max;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new long[N];
        for(int i = 0 ; i < N ; i++) {
            arr[i] = Long.parseLong(in.readLine());
            max = Math.max(max, arr[i]);
        }

        long le = 1, ri = M * max / N + 1;
        while(le <= ri) {
            long mid = (le + ri) / 2;
            long cnt = 0;
            for(int i = 0 ; i < N ; i++) {
                cnt += mid / arr[i];
            }

            if(cnt >= M) {
                ans = mid;
                ri = mid - 1;
            } else {
                le = mid + 1;
            }
        }

        System.out.println(ans);

    }
}
