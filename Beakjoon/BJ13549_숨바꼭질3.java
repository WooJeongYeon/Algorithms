package baekjoon;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

// dp vs bfs vs dfs
// dp -> 계속 더해지거나 계속 빼지는게 아니라 안됨
// bfs -> 2*x의 경우 0초 후라 최단시간 구하긴 애매
// dfs인가?? 근데 이걸로 하니까 stack overflow남ㅜㅜㅜ

// 예전 숨바꼭질1 풀이 보고 생각해봄... bfs로 하고 pq에 넣으면 최단시간거 뺄 수 있잖아? -> 맞음! -> 그냥 큐 써도 되넴...
// 인덱스만 pq에 넣고 사용해도 되네! 짜피 memo에 저장된 애만 갖다쓰니까...

public class BJ13549_숨바꼭질3 {
    static int size, N, K;
    static int[] memo;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        K = in.nextInt();
        size = Math.max(N, K) * 2 + 1;
        memo = new int[size];
        Arrays.fill(memo, Integer.MAX_VALUE);

        System.out.println(bfs());
    }

    static int bfs() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        pq.offer(new int[]{0, N});

        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            if(now[1] == K)
                return now[0];

            if(now[1] + 1 < size && memo[now[1] + 1] > now[0] + 1) {
                memo[now[1] + 1] = now[0] + 1;
                pq.offer(new int[]{now[0] + 1, now[1] + 1});
            }

            if(now[1] - 1 >= 0 && memo[now[1] - 1] > now[0] + 1) {
                memo[now[1] - 1] = now[0] + 1;
                pq.offer(new int[]{now[0] + 1, now[1] - 1});
            }

            if(now[1] * 2 < size && memo[now[1] * 2] > now[0]) {
                memo[now[1] * 2] = now[0];
                pq.offer(new int[]{now[0], now[1] * 2});
            }
        }
        return 0;
    }
}
