package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW1206_View {
    static int N, ans;
    static int[] delta = {-2, -1, 1, 2}, lenArr;
    static final int GAP = 2;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int tc = 1 ; tc <= 10 ; tc++) {
            N = Integer.parseInt(in.readLine());
            lenArr = new int[N];
            ans = 0;
            st = new StringTokenizer(in.readLine());

            for(int i = 0 ; i < N ; i++) {
                lenArr[i] = Integer.parseInt(st.nextToken());
            }

            for(int i = GAP ; i < N - GAP ; i++) {
                 int min = lenArr[i];
                 for(int j = 0 ; j < delta.length ; j++) {
                     min = Math.min(min, lenArr[i] - lenArr[i + delta[j]]);
                 }
                 if(min > 0)
                     ans += min;
            }


            sb.append("#" + tc + " " + ans + "\n");
        }

        System.out.println(sb);
    }
}
