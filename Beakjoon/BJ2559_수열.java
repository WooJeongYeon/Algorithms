package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2559_수열 {
    static int N, K, now, ans;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(in.readLine());
        for(int i = 0 ; i < K ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            now += arr[i];
        }
        ans = now;
        for(int i = K ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            now = now - arr[i - K] + arr[i];
            ans = Math.max(ans, now);
        }

        System.out.println(ans);
    }
}
