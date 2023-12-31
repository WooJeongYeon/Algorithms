package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * Date : 2022.03.20
 * Level : BaekJoon Gold 3
 * Difficulty : 상
 * URL : https://www.acmicpc.net/problem/11437
 * Select1 : 트리 - DFS로 한 번씩만 방문함(순환 X) -> visited 체크는 해줘야 해!
 * Thinking : LCA 사용
 *       1. 각 노드별 depth, 부모를 구하고
 *       2. 최소 공통 조상을 구하고자 하는 두 노드의 depth를 맞추고
 *       3. 루트노드를 향해 한 depth씩 타고 올라가면서 구함
 * Method : LCA(Lowest Common Ancestor)
 * Help : 하나씩 올라가면 되겠구나? 조금 생각했는데 도저히 모르겠어서 어떻게 푸는지 봄
 * Result : 정말 여러가지 방법이 있구나...
 * Plus1 : 참고 - https://4legs-study.tistory.com/121
 * Plus2 : b에 depth 더 긴애 저장하도록 바꿔놓고 조건문 안쓰고 depth 맞출 수 도 있네
 * Plus3 : 트리 내에서 두 정점의 최소 거리 구할 때 사용하기 좋대!
 */

public class BJ11437_LCA {
    static int N, M;
    static LinkedList<Integer>[] edges;
    static int[] depthArr;
    static int[] lastVertexArr;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        edges = new LinkedList[N + 1];
        depthArr = new int[N + 1];
        lastVertexArr = new int[N + 1];
        visited = new boolean[N + 1];
        for(int i = 1 ; i <= N ; i++) {
            edges[i] = new LinkedList<Integer>();
        }
        for(int i = 0 ; i < N - 1 ; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            edges[s].offer(e);
            edges[e].offer(s);
        }
        M = Integer.parseInt(in.readLine());

        dfs(1, 0, 1);

        for(int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(depthArr[a] < depthArr[b]) { // Depth 맞추는 작업
                while(depthArr[a] != depthArr[b]) {
                    b = lastVertexArr[b];
                }
            }
            else {
                while(depthArr[a] != depthArr[b]) {
                    a = lastVertexArr[a];
                }
            }

            while(a != b) {
                a = lastVertexArr[a];
                b = lastVertexArr[b];
            }
            sb.append(a + "\n");
        }


        System.out.println(sb.toString());
    }
    static void dfs(int now, int last, int depth){
        depthArr[now] = depth;
        lastVertexArr[now] = last;
        visited[now] = true;
        for(Integer n : edges[now]) {
            if(!visited[n])
                dfs(n, now, depth + 1);
        }
    }
}
