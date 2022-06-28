package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BJ11279_최대힙 {
    static int N;
    static PriorityQueue<Integer> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(in.readLine());
        pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for(int i = 0 ; i < N ; i++) {
            int x = Integer.parseInt(in.readLine());
            if(x != 0) {
                pq.offer(x);
                continue;
            }
            int ans = 0;
            if(!pq.isEmpty())
                ans = pq.poll();
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}
