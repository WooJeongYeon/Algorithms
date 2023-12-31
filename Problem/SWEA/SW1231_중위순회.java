package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW1231_중위순회 {
    static int idx, N;
    static char[] result;
    static int[][] tree;
    static final int LEFT = 0;
    static final int RIGHT = 1;
    static final int ALPHA = 2;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int tc = 1 ; tc <= 10 ; tc++) {
            idx = 0;
            N = Integer.parseInt(in.readLine());
            tree = new int[N + 1][3];
            result = new char[N];
            for(int i = 1 ; i <= N ; i++) {
                st = new StringTokenizer(in.readLine());
                int num = Integer.parseInt(st.nextToken());
                tree[num][ALPHA] = st.nextToken().charAt(0);
                int idx = 0;
                while(st.hasMoreTokens()) {
                    tree[num][idx++] = Integer.parseInt(st.nextToken());
                }
            }

            inorder(1);

            sb.append("#").append(tc).append(" ").append(new String(result)).append("\n");
        }

        System.out.println(sb);
    }

    static void inorder(int n) {
        if(tree[n][LEFT] != 0)
            inorder(tree[n][LEFT]);
        result[idx++] = (char)tree[n][ALPHA];
        if(tree[n][RIGHT] != 0)
            inorder(tree[n][RIGHT]);
    }
}
