package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * Date : 2022.02.13
 * Level : BaekJoon Gold 4
 * Difficulty : 중하
 * Time : 1H
 * URL : https://www.acmicpc.net/problem/9019
 * Thinking : String으로 왼쪽/오른쪽 이동하도록 하자 -> 이러면 무조건 자리수가 4자리여야 하므로 계속 채워줘야 함
 * Method : BFS
 * Result : 어떻게 계산할지 생각하는게 좀 걸렸다
 * Plus1 : 그냥 오른쪽 이동하는거나 왼쪽 이동하는걸 int로 쉽게 할 수 있었네. 괜히 String으로 했다가 시간만 더 걸렸다ㅠㅠ
 */
public class BJ9019_DSLR {
    static int TC;
    static String A, B;
    static boolean[] visited;
    static final int N = 10000;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        TC = Integer.parseInt(in.readLine());
        for(int tc = 0 ; tc < TC ; tc++) {
            st = new StringTokenizer(in.readLine(), " ");
            A = st.nextToken();
            B = st.nextToken();
            while(A.length() < 4) {
                A = "0" + A;
            }
            while(B.length() < 4) {
                B = "0" + B;
            }
            visited = new boolean[N];
            String ans = "";

            ans = bfs();

            sb.append(ans + "\n");
        }
        System.out.println(sb);
    }

    private static String bfs() {
        Queue<String[]> queue = new LinkedList<>();
        String ans = "";
        visited[Integer.parseInt(A)] = true;
        queue.offer(new String[]{A, ""});
        while(!queue.isEmpty()) {
            String[] now = queue.poll();
            if(now[0].equals(B)) {
                ans = now[1];
                break;
            }
            int n = Integer.parseInt(now[0]);
            int result = 0;

            result = (n * 2) % N;
            if(!visited[result]) {
                visited[result] = true;
                String s = result + "";
                while(s.length() < 4) {
                    s = "0" + s;
                }
                queue.offer(new String[]{s, now[1] + 'D'});
            }

            result = (N + n - 1) % N;
            if(!visited[result]) {
                visited[result] = true;
                String s = result + "";
                while(s.length() < 4) {
                    s = "0" + s;
                }
                queue.offer(new String[]{s, now[1] + 'S'});
            }

            result = Integer.parseInt(now[0].substring(1, 4) + now[0].charAt(0));
            if(!visited[result]) {
                visited[result] = true;
                queue.offer(new String[]{now[0].substring(1, 4) + now[0].charAt(0), now[1] + 'L'});
            }

            result = Integer.parseInt(now[0].charAt(3) + now[0].substring(0, 3));
            if(!visited[result]) {
                visited[result] = true;
                queue.offer(new String[]{now[0].charAt(3) + now[0].substring(0, 3), now[1] + 'R'});
            }
        }
        return ans;
    }
}
