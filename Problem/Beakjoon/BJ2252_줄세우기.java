package baekjoon;

// 진입 차수를 구하고
// 큐를 사용해 차수 0인 애들 계속 반복 -> 빌 때까지
// 매번 차수(N)만큼을 검사할 필요는 없을텐데.. -> 시간 어떻게 줄이지?
// 1. PQ + degrees 배열을 사용해봄(dijkstra처름 값 변동있으면 다 넣고 0만 꺼내게) -> 많이 줄어듬
// 2. 다른 사람 코드 봄
//   - Degree 계산할 때 다 돌 필요 없네!!! => edges 만들 때 세면 됨..
//   *** 이 방법 트리에서 루트노드 찾을 때 좋을듯..!!
//   - degree 0인 애들을 찾을 때 N개의 노드 전부를 검사할 필요 없네!! -> 값 변동있는 애들만 체크해주면 됨ㅠㅠㅠ -> 이거하니까 PQ보다 시간 더 준다..


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2252_줄세우기 {
    static int N, M;
    static int[] degrees;
    static LinkedList<Integer>[] edges;
    static Queue<Integer> queue;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        degrees = new int[N + 1];
        edges = new LinkedList[N + 1];
        queue = new LinkedList<>();

        for(int i = 1 ; i <= N ; i++) {
            edges[i] = new LinkedList<>();
        }
        for(int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());  // 여기서 degree 세어주면 됨
            degrees[b]++;
            edges[a].offer(b);
        }
        for(int i = 1 ; i <= N ; i++) {
            if(degrees[i] == 0)
                queue.offer(i);
        }

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int s = 0 ; s < size ; s++){
                int now = queue.poll();
                for(Integer e : edges[now]) {
                    if(--degrees[e] == 0)
                        queue.offer(e);
                }
                sb.append(now + " ");
            }
        }


        System.out.println(sb);
    }

}
