package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ15663_N과M9 {
    static int N, M;
    static int[] numArr, result;
    static boolean[] visited;
    static Set<String> set;
    static ArrayList<String> resultList;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numArr = new int[N];
        result = new int[M];
        visited = new boolean[N];
        set = new HashSet<>();
        resultList = new ArrayList<>();
        st = new StringTokenizer(in.readLine(), " ");
        for(int i = 0 ; i < N ; i++) {
            numArr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numArr);

        perm(0);

        for(String s : resultList) {
            sb.append(s + "\n");
        }

        System.out.println(sb.toString());
    }
    static void perm(int idx) {
        if(idx == M) {
            String s = "";
            for(int i =  0 ; i < M ; i++) {
                s += result[i] + " ";
            }
            if(!set.contains(s)) {
                resultList.add(s);
                set.add(s);
            }
            return;
        }
        for(int i = 0 ; i < N ; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            result[idx] = numArr[i];
            perm(idx + 1);
            visited[i] = false;
        }
    }
}
