package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 냄새 배열 -> ArrayList로 바꿈...
// 냄새는 마지막 냄새만 저장되는건가...?
// pq같은거 안쓰고 상어 맵에 놓아보면서 가장 작은 상어 저장되도록... -> pq 안써도 되고, 정렬도 안해도 되고
// sharks를 ArrayList 말고 배열에 저장(상어 num 오름차순으로 저장 가능) -> 이렇게 하면 처음에 정렬도 안해도 되고ㅠㅜㅜ
public class BJ19237_어른상어 {
    static int N, M, K, ans;
    static ArrayList<Smell>[][] smellArr;
    static ArrayList<Shark> sharks;
    static PriorityQueue<Shark> pq;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static final int END_POINT = 1000;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        smellArr = new ArrayList[N][N];
        sharks = new ArrayList<>();
        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            for(int j = 0 ; j < N ; j++) {
                smellArr[i][j] = new ArrayList<>();
                int num = Integer.parseInt(st.nextToken());
                if(num > 0) sharks.add(new Shark(i, j, num));
            }
        }
        Collections.sort(sharks, (o1, o2) -> o1.num - o2.num);
        st = new StringTokenizer(in.readLine(), " ");
        for(Shark shark : sharks) {
            shark.d = Integer.parseInt(st.nextToken()) - 1;
        }
        for(Shark shark : sharks) {
            int[][] dir = new int[4][4];
            for(int i = 0 ; i < 4 ; i++) {
                st = new StringTokenizer(in.readLine(), " ");
                for(int j = 0 ; j < 4 ; j++) {
                    dir[i][j] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
            shark.setPriority(dir);
        }

        while(true) {
            setSmell();
            if(sharks.size() <= 1 || ans > END_POINT) break;
            move();
            excludeShark();
            ans++;
        }

        if(ans > END_POINT) ans = -1;
        System.out.println(ans);
    }

    private static void excludeShark() {
        pq = new PriorityQueue<>();
        while(!sharks.isEmpty()) {
            pq.offer(sharks.remove(0));
        }
        Shark shark = null;
        while(true) {
            shark = pq.poll();
            if(pq.isEmpty()) break;
            if(shark.i == pq.peek().i && shark.j == pq.peek().j) continue;
            sharks.add(shark);
        }
        sharks.add(shark);
    }

    private static void move() {
        for(Shark shark : sharks) {
            int[] result = new int[4];
            for(int d = 0 ; d < 4 ; d++) {
                int ni = shark.i + di[shark.priority[shark.d][d]];
                int nj = shark.j + dj[shark.priority[shark.d][d]];
                if(ni < 0 || ni >= N || nj < 0 || nj >= N) {
                    result[d] = -1;
                    continue;
                }
                for(Smell smell : smellArr[ni][nj]) {
                    if(smell.num == shark.num) {
                        result[d] = 1;
                        break;
                    } else if(smell.num > 0) {
                        result[d] = 2;
                    }
                }
            }
            boolean isGo = false;
            for(int d = 0 ; d < 4 ; d++) {
                if(result[d] == 0) {
                    shark.i += di[shark.priority[shark.d][d]];
                    shark.j += dj[shark.priority[shark.d][d]];
                    shark.d = shark.priority[shark.d][d];
                    isGo = true;
                    break;
                }
            }
            if(isGo) continue;
            for(int d = 0 ; d < 4 ; d++) {
                if(result[d] == 1) {
                    shark.i += di[shark.priority[shark.d][d]];
                    shark.j += dj[shark.priority[shark.d][d]];
                    shark.d = shark.priority[shark.d][d];
                    break;
                }
            }
        }
    }

    private static void setSmell() {
        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < N ; j++) {
                if(smellArr[i][j].isEmpty()) continue;
                for(int k = smellArr[i][j].size() - 1 ; k >= 0  ; k--) {
                    if(--smellArr[i][j].get(k).time == 0) smellArr[i][j].remove(k);
                }
            }
        }
        for(Shark shark : sharks) {
            smellArr[shark.i][shark.j].add(new Smell(K, shark.num));
        }
    }

    static class Smell {
        int time, num;
        public Smell(int time, int num) {
            this.time = time;
            this.num = num;
        }
    }

    static class Shark implements Comparable<Shark>{
        int i, j, num, d;
        int[][] priority;

        public Shark(int i, int j, int num) {
            this.i = i;
            this.j = j;
            this.num = num;
        }
        public void setPriority(int[][] arr) {
            priority = arr;
        }

        @Override
        public int compareTo(Shark o) {
            if(this.i == o.i) {
                if(this.j == o.j) {
                    return o.num - this.num;
                }
                return this.j - o.j;
            }
            return this.i - o.i;
        }
    }
}
