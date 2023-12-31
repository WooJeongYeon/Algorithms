package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ13975_파일합치기3 {
    static int TC, N;
    static long ans;
    static PriorityQueue<Long> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        TC = Integer.parseInt(in.readLine());
        for(int tc = 0 ; tc < TC ; tc++) {
            N = Integer.parseInt(in.readLine());
            ans = 0;
            pq = new PriorityQueue<>();
            st = new StringTokenizer(in.readLine());
            for(int i = 0 ; i < N ; i++) {
                int x = Integer.parseInt(st.nextToken());
                pq.offer((long)x);
            }

            for(int i = 0 ; i < N - 1 ; i++) {
                long x = pq.poll();
                long y = pq.poll();

                ans += x + y;
                pq.offer(x + y);
            }

            sb.append(ans + "\n");
        }

        System.out.print(sb);
    }
}
