package baekjoon;

import java.util.Scanner;
/*
 * Date : 2022.03.20
 * Level : BaekJoon Bronze 3
 * Difficulty : 하
 * URL : https://www.acmicpc.net/problem/1085
 */
public class BJ1085_직사각형에서탈출 {
    static int x, y, w, h;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        x = in.nextInt();
        y = in.nextInt();
        w = in.nextInt();
        h = in.nextInt();
        System.out.println(Math.min(x, Math.min(y, Math.min(w - x, h - y))));
    }
}
