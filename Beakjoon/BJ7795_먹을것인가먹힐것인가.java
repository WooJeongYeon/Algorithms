package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ7795_먹을것인가먹힐것인가 {
    static int TC, A, B, ans;
    static int[] arr1, arr2;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        TC = Integer.parseInt(in.readLine());
        for(int tc = 0  ; tc < TC ; tc++) {
            st = new StringTokenizer(in.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            arr1 = new int[A];
            arr2 = new int[B];
            ans = 0;

            st = new StringTokenizer(in.readLine());
            for(int i = 0 ; i < A ; i++) {
                arr1[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(in.readLine());
            for(int i = 0 ; i < B ; i++) {
                arr2[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr2);

            for(int i = 0 ; i < A ; i++) {
                int n = arr1[i];
                int le = 0, ri = arr2.length - 1;
                int result = 0;
                while(le <= ri) {
                    int mid = (le + ri) / 2;
                    if(arr2[mid] < n) {
                        result = mid + 1;
                        le = mid + 1;
                    } else {
                        ri = mid - 1;
                    }
                }
                ans += result;
            }
            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }
}
