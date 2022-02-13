package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
/*
 * Date : 2022.02.13
 * Level : BaekJoon Gold 4
 * Difficulty : 중하
 * URL : https://www.acmicpc.net/problem/17298
 * Thinking : stack에 내림차순으로 저장되도록!
 * Method : stack
 * Result : 프로그래머스에서 비슷하게 푼 문제 있었음
 */
public class BJ17298_오큰수 {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        Stack<int[]> stack = new Stack<>(); // 0 - 인덱스, 1 - 값
        arr = new int[N];

        for(int i = 0 ; i < N ; i++) {
            int n = Integer.parseInt(st.nextToken());
            while(!stack.isEmpty() && stack.peek()[1] < n) {
                arr[stack.pop()[0]] = n;
            }

            stack.add(new int[]{i, n});
        }

        while(!stack.isEmpty()) {  // 남아있는 애들은 오른쪽에 자기보다 큰 수가 없는 거
            arr[stack.pop()[0]] = -1;
        }

        for(int n : arr) {
            sb.append(n + " ");
        }
        System.out.println(sb);
    }
}
