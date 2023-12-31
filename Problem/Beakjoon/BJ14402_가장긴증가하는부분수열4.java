package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14402_가장긴증가하는부분수열4 {
    static int N, len, lenIdx;
    static int[] lastValArr, dp, arr;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        lastValArr = new int[N + 1];
        dp = new int[N + 1];
        arr = new int[N + 1];

        for(int i = 1 ; i <= N ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            int max = 0, maxIdx = 0;
            for(int j = 1 ; j < i ; j++) {
                if(arr[j] < arr[i] && max < dp[j]) {
                    max = dp[j];
                    maxIdx = j;
                }
            }
            dp[i] = dp[maxIdx] + 1;
            lastValArr[i] = maxIdx;
            if(len < dp[i]) {
                len = dp[i];
                lenIdx = i;
            }
        }
        sb.append(len + "\n");

        StringBuilder s = new StringBuilder();
        while(lenIdx != 0) {
            s.insert(0, arr[lenIdx] + " ");
            lenIdx = lastValArr[lenIdx];
        }
        s.setLength(s.length() - 1);

        sb.append(s.toString());
        System.out.println(sb.toString());
    }
}
