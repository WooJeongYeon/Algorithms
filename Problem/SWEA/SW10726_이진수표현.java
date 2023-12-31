package swea;

import java.util.Scanner;

public class SW10726_이진수표현 {
    static int TC, N, M, result;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        TC = in.nextInt();
        for(int tc = 1 ; tc <= TC ; tc++) {
            N = in.nextInt();
            M = in.nextInt();
            result = 1;
            for(int i = 0 ; i < N ; i++) {
                result &= (M >> i);
            }
            sb.append("#").append(tc).append(" ").append(result == 1 ? "ON" : "OFF").append("\n");
        }

        System.out.println(sb);
    }
}
