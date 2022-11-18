package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ9095_123더하기 {
    static int TC, N, ans;
    static int[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        TC = Integer.parseInt(in.readLine());
        result = new int[12];

        recur(0);

        for(int tc = 1 ; tc <= TC ; tc++) {
            N = Integer.parseInt(in.readLine());

            sb.append(result[N]).append("\n");
        }

        System.out.println(sb);
    }

    static void recur(int n) {
        if(n > 11)
            return;
        result[n]++;
        recur(n + 1);
        recur(n + 2);
        recur(n + 3);
    }
}
