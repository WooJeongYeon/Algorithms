package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class SW9480_민정이와광직이의알파벳공부 {
    static int TC, N, ans;
    static String[] wordArr;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        TC = Integer.parseInt(in.readLine());

        for(int tc = 1 ; tc <= TC ; tc++) {
            ans = 0;
            N = Integer.parseInt(in.readLine());
            wordArr = new String[N];
            visited = new boolean[N];
            for(int i = 0 ; i < N ; i++) {
                wordArr[i] = in.readLine();
            }

            subset(0);

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.println(sb);
    }

    static void subset(int idx) {
        if(idx == N) {
            Set<Character> alphaSet = new HashSet<>();
            for(int i = 0 ; i < N ; i++) {
                if(visited[i])
                    continue;
                for(int j = 0 ; j < wordArr[i].length() ; j++) {
                    alphaSet.add(wordArr[i].charAt(j));
                }
            }
            if(alphaSet.size() == ('Z' - 'A' + 1)) {
                ans++;
            }
            return;
        }
        visited[idx] = true;
        subset(idx + 1);
        visited[idx] = false;
        subset(idx + 1);
    }
}
