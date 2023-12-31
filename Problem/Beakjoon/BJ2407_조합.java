package baekjoon;

import java.math.BigInteger;
import java.util.Scanner;

/*
 * Date : 2022.06.19
 * Level : BaekJoon Sliver 4
 * Difficulty : 중하
 * URL : https://www.acmicpc.net/problem/2407
 * Thinking : 출력값이 long도 넘어가서 BigInteger 사용
 * Method : 이항정리, DP, BigInt
 * Plus1 : 사실 bigint쓸거면 그냥 곱하고 나누면 되긴하네
 */


public class BJ2407_조합 {
    static int N, M;
    static BigInteger[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new BigInteger[N + 1];
        arr[0] = arr[1] = BigInteger.ONE;
        for(int i = 2 ; i <= N ; i++) {
            arr[i] = BigInteger.ONE;
            for(int j = i - 1 ; j > 0 ; j--) {
                arr[j] = arr[j - 1].add(arr[j]);
            }
        }

        System.out.println(arr[M]);
    }
}
