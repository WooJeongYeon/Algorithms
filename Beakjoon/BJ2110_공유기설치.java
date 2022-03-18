package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * Date : 2022.03.18
 * Level : BaekJoon Gold 5
 * Difficulty : 중
 * Why : 저번에 못풀었다가 다시푸니까 풀림
 * URL : https://www.acmicpc.net/problem/2110
 * Thinking : 공유기는 맨왼쪽, 맨오른쪽(끝)에 놓는게 가장 좋음!(거리를 가능한 크게 하는 문제니까) -> 그러므로 맨 첫번째에 공유기 놓고 시작해도 됨
 * Method : Binary Search
 * Error1 : 왜틀렸지? -> 한개만 작은 값이면 되니까 결과가 공유기 여러개 설치하라고 나온다해도 가능한 것!(하나 빼고 다른 애들의 거리를 넓히면 됨)
 * Result : 전에 문제 이해를 잘못해서 못풀었었음. 문제 잘 읽고, 머리든 손으로든 시간초과안나게 설계 다 하고 구현하자! 그리고 아무리봐도 맞으면 어딘가 잘못 생각하는게 있으니 30분이라도 더 생각해보자 
 */

public class BJ2110_공유기설치 {
    static int N, C, ans;
    static LinkedList<Integer> houseList;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        houseList = new LinkedList<>();
        for(int i = 0 ; i < N ; i++){
            houseList.offer(Integer.parseInt(in.readLine()));
        }

        Collections.sort(houseList);

        int left = 0, right = 1000000000, mid;
        int cnt;
        while(left <= right) {
            mid = (left + right) / 2;
            cnt = 1;
            int lastPos = houseList.peekFirst();
            for(Integer n : houseList) {
                if(n - lastPos >= mid) {
                    cnt++;
                    lastPos = n;
                }
            }

            if(cnt >= C) {
                ans = Math.max(ans, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(ans);
    }
}
