package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// StringBuilder로 다 바꾸고
// result를 다 만들고 제거하는 식으로
// delete...

public class BJ9935_문자열폭발 {
    static StringBuilder origin, key, result;
    static Stack<Integer> stack;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        origin = new StringBuilder(in.readLine());
        key = new StringBuilder(in.readLine());
        result = new StringBuilder();
        stack = new Stack<>();
        int len = 0;
        for(int i = 0 ; i < origin.length() ; i++) {
            if (origin.charAt(i) == key.charAt(len)) {
                len++;
                if (len == key.length()) {
                    len = stack.isEmpty() ? 0 : stack.pop();
                    result.delete(result.length() - key.length() + 1, result.length());
                    continue;
                }
            } else if(origin.charAt(i) == key.charAt(0)) {
                stack.add(len);
                len = 1;
            } else if(origin.charAt(i) != key.charAt(0)) {
                stack.clear();
                len = 0;
            }
            result.append(origin.charAt(i));
        }
        if(result.toString().equals("")) result.append("FRULA");
        System.out.println(result.toString());
    }
}
