package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ1991_트리순회_이차원 {
    static int N;
    static int[][] arr;
    static StringBuilder sb1, sb2, sb3;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb1 = new StringBuilder();
        sb2 = new StringBuilder();
        sb3 = new StringBuilder();

        N = Integer.parseInt(in.readLine());
        arr = new int[N][2];
        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(in.readLine());
            int a = st.nextToken().charAt(0) - 'A';
            int b = st.nextToken().charAt(0) - 'A';
            int c  = st.nextToken().charAt(0) - 'A';
            arr[a][0] = b;
            arr[a][1] = c;
        }

        dfs(0);

        System.out.println(sb1);
        System.out.println(sb2);
        System.out.println(sb3);
    }

    static void dfs(int idx) {
        sb1.append((char)(idx + 'A'));
        if(arr[idx][0] >= 0) {
            dfs(arr[idx][0]);
        }
        sb2.append((char)(idx + 'A'));
        if(arr[idx][1] >= 0) {
            dfs(arr[idx][1]);
        }
        sb3.append((char)(idx + 'A'));
    }
}
