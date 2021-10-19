package day0806;

import java.util.Scanner;
import java.util.Stack;
/*
 * Date : 210806
 */
public class SW1861_Square_DFS {
    // 델타 배열
    static int[] di = {-1, 1, 0, 0};    // 위, 아래, 왼쪽, 오른쪽
    static int[] dj = {0, 0, -1, 1};
    static int TC;            // 테스트 케이스
    static int N;            // 배열의 크기
    static int max = -1, ans = 1000001;        // 이동할 수 있는 최대의 방 개수, 그 방에 적힌 수를 저장
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        TC = in.nextInt();        // 테스트 케이스 입력받음
        for(int tc = 1 ; tc <= TC ; tc++) {        // TC만큼 반복
            N = in.nextInt();                    // 배열의 크기
            int[][] map = new int[N][N];        // map
            for(int i = 0 ; i < N ; i++) {        // 배열 입력받아서 저장
                for(int j = 0 ; j < N ; j++) {
                    map[i][j] = in.nextInt();
                }
            }

            for(int i = 0 ; i < N ; i++) {        // 배열만큼 반복
                for(int j = 0 ; j < N ; j++) {
                    Point start = new Point(i, j);        // 처음 위치를 저장
                    int sum = 1;                        // 처음 위치도 포함이므로 1로 저장
                    Stack<Point> stack = new Stack<Point>();        // 일치하는 주변 값을 넣어줄 stack
                    stack.push(start);                    // stack에 처음 위치를 넣어줌
                    while(!stack.isEmpty()) {            // stack이 빌 때까지 반복
                        Point now = stack.pop();        // 스택의 요소를 꺼냄
                        for(int d = 0 ; d < 4 ; d++) {    // 상하좌우 네 방향에 대해
                            Point p = new Point(now.i + di[d], now.j + dj[d]);    // 해당 방향의 위치에 대해 Point 객체 생성
                            // 배열을 벗어나지 않고, 1만큼 크다면
                            if(p.i >= 0 && p.i < N && p.j >= 0 && p.j < N && (map[p.i][p.j] == map[now.i][now.j] + 1)) {
                                stack.push(p);    // 스택에 넣고
                                sum++;            // 방 개수 증가
                            }
                        }
                    }
                    if(max < sum) {                // 구한 방 개수가 최대의 방 개수보다 크다면
                        max = sum;                // 최대 방 개수와 적힌 수를 새로 저장
                        ans = map[start.i][start.j];
                    }
                    else if(max == sum && ans > map[start.i][start.j])    // 방 개수가 같고, 적힌 수가 작다면
                        ans = map[start.i][start.j];    // 작은 적힌 수를 저장
                }
            }
            System.out.println("#" + tc + " " + ans + " " + max);        // 결과 출력
        }
    }
    // 2차원 배열에서의 행, 열 값을 저장할 클래스
    static class Point {
        int i;
        int j;
        Point(int i, int j) {
            this.i = i;
            this.j = j;
        }

    }
}

