package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2042_구간합구하기 {
    static int N, M, K;
    static long[] arr;
    static long[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new long[N + 1];
        tree = new long[N * 4 + 1];
        for(int i = 1 ; i <= N ; i++) {
            arr[i] = Long.parseLong(in.readLine());
        }
        init(1, 1, N);
        for(int i = 0 ; i < M + K ; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if(a == 1) {
                update(b, c, 1, 1, N);
            } else {
                long sum = intervalSum(b, (int)c, 1, 1, N);
                sb.append(sum + "\n");
            }
        }

        System.out.println(sb.toString());
    }
    static long init(int node, int left, int right) {
        if(left == right) {
            return tree[node] = arr[left];
        }
        int mid = (left + right) / 2;
        long leftVal = init(node * 2, left, mid);
        long rightVal = init(node * 2 + 1, mid + 1, right);
        return tree[node] = leftVal + rightVal;
    }

    static long intervalSum(int start, int end, int node, int left, int right) {
        if(left > end || right < start) {
            return 0;
        }
        if(start <= left && right <= end) {
            return tree[node];
        }
        int mid = (left + right) / 2;

        long leftVal = intervalSum(start, end , node * 2, left, mid);
        long rightVal = intervalSum(start, end,node * 2 + 1, mid + 1, right);
        return leftVal + rightVal;
    }
    static long update(int index, long newVal, int node, int left, int right) {
        if(index < left || right < index) {
            return tree[node];
        }
        if(left == right) {
            return tree[node] = newVal;
        }
        int mid = (left + right) / 2;

        long leftVal = update(index, newVal, node * 2, left, mid);
        long rightVal = update(index, newVal, node * 2 + 1, mid + 1, right);
        return tree[node] = leftVal + rightVal;
    }
}
