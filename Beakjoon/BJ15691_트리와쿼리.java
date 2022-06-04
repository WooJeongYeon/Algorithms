package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * Date : 2022.06.05
 * Level : BaekJoon Gold 5
 * Difficulty : 하
 * URL : https://www.acmicpc.net/problem/15681
 * Select1 : 아래서부터 올라갈까?(같은 데 여러번 방문, edges 안써도 되서 메모리 아낌) VS 위에서부터 내려갈까?(DFS로 한번씩만 방문, edges 사용)
 *          - 어짜피 어디가 부모고 자식인지 모르니까 edges 써야겠넴...
 * Thinking : DFS 돌면서 개수 더해주기
 * Method : DFS, Tree
 * Plus1 : visited 안주고 이전 노드 번호 저장해서 다시 방문하게 안할 수 있넴... -> 시간 더 느려짐...ㅋㅋㅋㅋㅋㅋㅋ
 */

public class BJ15691_트리와쿼리 {
    static int N, R, Q;
    static int[] childCntArr;
    static LinkedList<Integer>[] edges;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        childCntArr = new int[N + 1];
        edges = new LinkedList[N + 1];
        visited = new boolean[N + 1];
        for(int i = 1 ; i <= N ; i++) {
            edges[i] = new LinkedList<>();
        }

        for(int i = 0 ; i < N - 1 ; i++) {
            st = new StringTokenizer(in.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            edges[s].offer(e);
            edges[e].offer(s);
        }

        dfs(R);

        for(int i = 0 ; i < Q ; i++) {
            int q = Integer.parseInt(in.readLine());
            sb.append(childCntArr[q]).append("\n");
        }

        System.out.println(sb);
    }

    static int dfs(int now) {
        visited[now] = true;
        int sum = 1;
        for(Integer n : edges[now]) {
            if(!visited[n])
                sum += dfs(n);
        }
        childCntArr[now] = sum;
        return childCntArr[now];
    }
}
