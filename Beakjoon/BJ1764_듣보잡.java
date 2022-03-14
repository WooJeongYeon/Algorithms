package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ1764_듣보잡 {
    static int N, M;
    static Set<String> set;
    static PriorityQueue<String> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        set = new HashSet<>();
        pq = new PriorityQueue<>();
        for(int i = 0 ; i < N ; i++) {
            set.add(in.readLine());
        }
        for(int i = 0 ; i < M ; i++) {
            String s = in.readLine();
            if(set.contains(s)) {
                pq.offer(s);
            }
        }
        sb.append(pq.size() + "\n");
        while(!pq.isEmpty()) {
            sb.append(pq.poll() + "\n");
        }
        System.out.println(sb.toString());
    }

}
