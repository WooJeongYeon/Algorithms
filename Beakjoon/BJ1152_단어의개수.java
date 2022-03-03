package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1152_단어의개수 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String s = in.readLine().trim();
        System.out.println(s.length() == 0 ? 0 : s.trim().split(" ").length);
    }
}
