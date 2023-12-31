package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
 * Date : 2022.06.19
 * Level : BaekJoon Gold 4
 * Difficulty : 중
 * Why : 내가 못풀어서ㅠㅠㅠ
 * URL : https://www.acmicpc.net/problem/20040
 * Thinking : 같은 선분을 또 그을 수 없음! 그러니까 트리의 경우 생각해보면 (정점개수 <= 간선개수)이면 순환이 생기잖아
 *          - 그럼 간선 개수로 체크할 수 있지 않을까?
 * Method : Union-find
 * Error1 : 제출하자마자 틀렸다! -> 집합이 여러개인 경우 위의 공식이 안맞음ㅠㅠㅠ 
 * Help : 어떻게 푸는지 찾아봤다ㅠㅠㅠ 서로소 집합으로 엄청 쉽게 풀 수 있네... 이미 같은 집합에 있는 애들이면 -> 순환 생김
 * Result : 집합으로 푸는걸
 */

public class BJ20040_사이클게임 {
    static int N, M, ans;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N + 1];

        make();

        for(int i = 1 ; i <= M ; i++) {
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(!union(a, b)) {
                ans = i;
                break;
            }
        }

        System.out.println(ans);
    }

    static void make() {
        for(int i = 1 ; i <= N ; i++) {
            parents[i] = i;
        }
    }

    static int find(int n) {
        if(parents[n] == n)
            return n;
        return parents[n] = find(parents[n]);
    }

    static boolean union(int a, int b) {
        int nodeA = find(a);
        int nodeB = find(b);
        if(nodeA == nodeB)
            return false;
        parents[nodeA] = nodeB;
        return true;
    }
}
