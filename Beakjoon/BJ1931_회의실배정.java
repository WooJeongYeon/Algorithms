package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1931_회의실배정 {
    static int N, last, ans;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        arr = new int[N][2];
        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(in.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, ((o1, o2) -> {
            if(o1[1] == o2[1])
                return o1[0] - o2[0];
            return o1[1] - o2[1];
        }));
        for(int i = 0 ; i < N ; i++) {
            if(arr[i][0] >= last) {
                last = arr[i][1];
                ans++;
            }
        }

        System.out.println(ans);
    }
}
