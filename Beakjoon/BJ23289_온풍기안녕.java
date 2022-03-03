package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
 * Date : 2022.03.04
 * Level : BaekJoon Gold 1
 * Difficulty : 중상
 * Why : 문제가 너무 길고 할 게 많았다
 * Time : 2시간 좀 넘게
 * URL : https://www.acmicpc.net/problem/23289
 * Select1 : 사이에 벽 있냐없냐 -> 3차원 배열로 저장함
 * Select2 : 처음에 공기청정기 온도 한 번만 구해놓고 그 이후에는 저장하논 airBlowerTemp로 계속 더해줌
 * Select3 : 델타매열 상우하좌로 하려고 changeDir 배열로 input 받은 방향 바꿔서 저장함
 * Select4 : 원래 온풍기 바람 for문으로 하려다가 dfs로 했는데 편하당...
 * Thinking :
        - 초콜릿을 하나 먹는다.
        - 집에 있는 모든 온풍기에서 바람이 한 번 나옴
        - 온도가 조절됨
        - 온도가 1 이상인 가장 바깥쪽 칸의 온도가 1씩 감소
        - 조사하는 모든 칸의 온도가 K 이상이 되었는지 검사. 모든 칸의 온도가 K이상이면 테스트를 중단하고, 아니면 1부터 다시 시작한다.
 * Method : simulation, DFS
 * Error1 : dfs할 때 num을 빼야되는데 더했었네..(5에서 0까지 가야되는데)
 * Error2 : 가장 바깥쪽 칸이 테두리가 아니라 0이 아닌 온도 뭉치의 가장자리인 줄 알았지... dfs 쓰고 있었음..
 * Error3 : 답이 100 넘으면 101로 하고 끝내는거 빼먹었음
 * Result : 삼성 어려워.. 난이도 1이라니ㅠㅠㅠ 그래도 머리쓰는 문제보다는 무작정 푸는 시뮬레이션이 나은거 같기도 하고
 * Plus1 : 온도 조절하는 부분에서 나는 두번씩 될까바 visited 배열로 체크했는데 -> 그냥 현재 칸이 큰 경우만 온도 조절하게 하면 되네! -> 그럼 한 번만 계산함
 */
public class BJ23289_온풍기안녕 {
    static int R, C, K, W, ans;
    static int[][] map, airBlowerTemp;
    static ArrayList<int[]> tempPos, airBlowerPos;
    static boolean[][][] walls;
    static boolean[][] visited;
    static int[] changeDir = {1, 3, 0, 2};
    static int[] di = {-1, 0, 1, 0};   // 상우하좌
    static int[] dj = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        init();
        setAirBlowerTemp();
        performanceTest();

        System.out.println(ans);
    }

    private static void performanceTest() {
        while(true) {
            ans++;
            blowAirBlower();
            controlTemperate();
            downTemperate();
            if(checkTemperate()) break;
            if(ans > 100) break;
        }
    }

    private static boolean checkTemperate() {
        boolean isPass = true;
        for(int[] pos : tempPos) {
            if(map[pos[0]][pos[1]] < K) {
                isPass = false;
                break;
            }
        }
        return isPass;
    }

        private static void downTemperate() {
        for(int i = 0 ; i < R ; i++) {
            for(int j = 0 ; j < C ; j++) {
                if((i == 0 || i == R - 1 || j == 0 || j == C - 1) && map[i][j] >= 1) map[i][j]--;
            }
        }
    }

    private static void controlTemperate() {
        int[][] newMap = new int[R][C];
        for(int i = 0 ; i < R ; i++) {
            newMap[i] = map[i].clone();
        }
        boolean[][][] isGo = new boolean[R][C][4];
        for(int i = 0 ; i < R ; i++) {
            for(int j = 0 ; j < C ; j++) {
                int now = map[i][j];
                for(int d = 0 ; d < 4 ; d++) {
                    int ni = i + di[d];
                    int nj = j + dj[d];
                    if(isOutOfIdx(ni, nj) || isGo[i][j][d] || walls[i][j][d]) continue;
                    int sub = Math.abs((map[i][j] - map[ni][nj]) / 4);
                    if(map[ni][nj] < now) {
                        newMap[ni][nj] += sub;
                        newMap[i][j] -= sub;
                    } else {
                        newMap[ni][nj] -= sub;
                        newMap[i][j] += sub;
                    }
                    isGo[i][j][d] = isGo[ni][nj][(d + 2) % 4] = true;
                }
            }
        }
        map = newMap;
    }

    private static void blowAirBlower() {
        for(int i = 0 ; i < R ; i++) {
            for(int j = 0 ; j < C ; j++) {
                map[i][j] += airBlowerTemp[i][j];
            }
        }
    }

    private static void setAirBlowerTemp() {
        for(int[] airBlower : airBlowerPos) {
            int d = airBlower[2];
            int ni = di[d] + airBlower[0];
            int nj = dj[d] + airBlower[1];
            if(isOutOfIdx(ni, nj) || walls[airBlower[0]][airBlower[1]][d]) continue;
            visited = new boolean[R][C];
            dfs1(ni, nj, airBlower[2], 5);
        }
    }

    private static void dfs1(int i, int j, int d, int num) {
        if(num == 0) return;
        visited[i][j] = true;
        airBlowerTemp[i][j] += num;
        int leftD = (d + 3) % 4;
        int rightD = (d + 1) % 4;
        int ni, nj;
        ni = i + di[leftD] + di[d];
        nj = j + dj[leftD] + dj[d];
        if(!isOutOfIdx(i + di[leftD], j + dj[leftD]) && !isOutOfIdx(ni, nj) && !walls[i][j][leftD] && !walls[i + di[leftD]][j + dj[leftD]][d]
                && !visited[ni][nj]) {
            dfs1(ni, nj, d, num - 1);
        }
        ni = i + di[d];
        nj = j + dj[d];
        if(!isOutOfIdx(ni, nj) && !walls[i][j][d] && !visited[ni][nj]) {
            dfs1(ni, nj, d, num - 1);
        }
        ni = i + di[rightD] + di[d];
        nj = j + dj[rightD] + dj[d];
        if(!isOutOfIdx(i + di[rightD], j + dj[rightD]) && !isOutOfIdx(ni, nj) && !walls[i][j][rightD] && !walls[i + di[rightD]][j + dj[rightD]][d]
                && !visited[ni][nj]) {
            dfs1(ni, nj, d, num - 1);
        }
    }

    static boolean isOutOfIdx(int i, int j) {
        return i < 0 || i >= R || j < 0 || j >= C;
    }
    static void init() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        airBlowerTemp = new int[R][C];
        tempPos = new ArrayList<>();
        airBlowerPos = new ArrayList<>();
        walls = new boolean[R][C][4];
        for(int i = 0 ; i < R ; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            for(int j = 0 ; j < C ; j++) {
                int num = Integer.parseInt(st.nextToken());
                if(1 <= num && num <= 4)
                    airBlowerPos.add(new int[]{i, j, changeDir[num - 1]});
                else if(num == 5) {
                    tempPos.add(new int[]{i, j});
                }
            }
        }
        W = Integer.parseInt(in.readLine());
        for(int i = 0 ; i < W ; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());
            if(t == 0) {
                walls[x - 1][y][2] = true;      // 아래방향
                walls[x][y][0] = true;             // 위방향에 벽
            } else if(t == 1){
                walls[x][y][1] = true;           // 오른쪽
                walls[x][y + 1][3] = true;       // 왼쪽
            }
        }
    }
}
