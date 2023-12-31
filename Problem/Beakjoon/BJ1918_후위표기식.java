package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
 * Date : 2022.06.20
 * Level : BaekJoon Gold 2
 * Difficulty : 중하
 * Why : 예전에 자료구조 할 떄 구현한 적 있음
 * Time :
 * URL : https://www.acmicpc.net/problem/1918
 * Thinking : 우선순위 부여
 *          - 경우를 나눔
 * Method : stack
 * Result : 자료구조 수업 들을때랑 거의 비슷하게 짰넹... 신기(그떄는 C++)
 */

public class BJ1918_후위표기식 {
    static Map<Character, Integer> map;
    static String[] op = {"+-", "*/", "()"};    // 우선순위 차례대로

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s = in.readLine();
        Stack<Character> stack = new Stack<>();
        setMap();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ('A' <= c && c <= 'Z')       // 알파벳이면 출력
                sb.append(c);  
            else if (c == ')') {            // )이면 스택에 들어있는 연산자들 모두 출력
                while (!stack.isEmpty()) {
                    char tmp = stack.pop();
                    if (tmp == '(')
                        break;
                    sb.append(tmp);
                }
            } else {                        // 그 외이면 우선순위 비교
                while (!stack.isEmpty()) {
                    if (map.get(stack.peek()) < map.get(c) || stack.peek() == '(')
                        break;
                    sb.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb);
    }

    static void setMap() {
        map = new HashMap<>();
        for (int i = 0; i < op.length; i++) {
            map.put(op[i].charAt(0), i);
            map.put(op[i].charAt(1), i);
        }
    }
}
