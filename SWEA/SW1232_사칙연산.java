package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW1232_사칙연산 {
    static int N, ans;
    static int[][] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int tc = 1 ; tc <= 10 ; tc++) {
            N = Integer.parseInt(in.readLine());
            tree = new int[N + 1][3];
            for(int i = 0 ; i < N ; i++) {
                st = new StringTokenizer(in.readLine());
                int n = Integer.parseInt(st.nextToken());
                if(st.countTokens() > 1) {
                    tree[n][0] = st.nextToken().charAt(0);
                    tree[n][1] = Integer.parseInt(st.nextToken());
                    tree[n][2] = Integer.parseInt(st.nextToken());
                } else {
                    tree[n][0] = Integer.parseInt(st.nextToken());
                }
            }

            ans = calculate(1);

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.println(sb);
    }

    static int calculate(int n) {
        if(tree[n][1] == 0)
            return tree[n][0];
        int leftVal = calculate(tree[n][1]);
        int rightVal = calculate(tree[n][2]);
        int result = 0;
        switch(tree[n][0]) {
            case '+' :
                result = leftVal + rightVal;
                break;
            case '-' :
                result = leftVal - rightVal;
                break;
            case '*' :
                result = leftVal * rightVal;
                break;
            case '/' :
                result = leftVal / rightVal;
                break;
        }

        return result;
    }
}
