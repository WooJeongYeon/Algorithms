package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 시간초과... -> cnt를 한번에 가져와야 함

public class BJ4195_친구네트워크 {
    static int TC, F, N;
    static int[] parents, cntArr;
    static String[][] edges;
    static Map<String, Integer> map;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        TC = Integer.parseInt(in.readLine());
        for(int tc = 0 ; tc < TC ; tc++) {
            F = Integer.parseInt(in.readLine());
            map = new HashMap<>();
            edges = new String[F][2];
            N = 0;
            // 친구 몇명인지 구하기
            for(int i = 0 ; i < F ; i++) {
                edges[i] = in.readLine().split(" ");
                for(int j = 0 ; j < 2 ; j++) {
                    if(!map.containsKey(edges[i][j])) {
                        map.put(edges[i][j], N++);
                    }
                }
            }
            make();
            for(int i = 0 ; i < F ; i++) {
                union(map.get(edges[i][0]), map.get(edges[i][1]));
                int root = find(map.get(edges[i][1]));
                sb.append(cntArr[root] + "\n");
            }
        }
        System.out.println(sb.toString());
    }
    static void make() {
        parents = new int[N];
        cntArr = new int[N];
        for(int i = 0 ; i < N ; i++) {
            parents[i] = i;
            cntArr[i] = 1;
        }
    }
    static int find(int n) {
        if(parents[n] == n) return n;
        return parents[n] = find(parents[n]);
    }
    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if(rootA != rootB) {
            parents[rootA] = rootB;
            cntArr[rootB] += cntArr[rootA]; // 합치고 root로다가 cnt 더해줌 -> 하나의 집합이 됨
            cntArr[rootA] = 0;
        }
    }
}
