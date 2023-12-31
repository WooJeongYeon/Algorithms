package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ2075_N번째큰수 {
    static int N, ans;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((o1, o2) -> o2 - o1);
        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(in.readLine());
            for(int j = 0 ; j < N ; j++) {
                pq.offer(Integer.parseInt(st.nextToken()));
            }
        }

        int idx = 1;
        while(!pq.isEmpty()) {
            if(idx == N) {
                ans = pq.poll();
                break;
            }
            pq.poll();
            idx++;
        }

        System.out.println(ans);
    }
}
