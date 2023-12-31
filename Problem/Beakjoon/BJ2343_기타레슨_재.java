package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2343_기타레슨_재 {
    static int N, M, ans;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(in.readLine());
        for(int i = 0 ; i < N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int le = 1, ri = 1000000000;
        while(le <= ri) {
            int mid = (le + ri) / 2;
            int cnt = 1;
            int len = 0;
            for(int i = 0 ; i < N ; i++) {
                if(arr[i] > mid) {
                    cnt = M + 1;
                    break;
                }
                if(len + arr[i] <= mid)
                    len += arr[i];
                else {
                    cnt++;
                    len = arr[i];
                }
            }

            if(cnt <= M) {
                ans = mid;
                ri = mid - 1;
            } else {
                le = mid + 1;
            }
        }

        System.out.println(ans);
    }
}
