package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ11047_동전0 {
    static int N, K, ans;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for(int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(in.readLine());
        }
        for(int i = N - 1 ; i >= 0 ; i--) {
            if(K == 0) break;
            ans += K / arr[i];
            K = K % arr[i];
        }
        System.out.println(ans);
    }
}
