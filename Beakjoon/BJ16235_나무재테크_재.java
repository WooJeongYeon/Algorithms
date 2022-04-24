package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 이거 추가 안했었다가... 와 진짜 못푸네 어떻게하지 내가 이전에 푼거 보고 틀린거 찾아냄ㅠㅠㅠ 자동으로 정렬 된다고 생각했어..
// 정렬도 age만 하면 되고
// tmptree도 한번 만들어서 spring에 tmp로 옮기고 fall에 tmp를 tree로 옮기면 되자너...ㅋㅋㅋㅋㅋㅋ
// 예전이 훨씬 잘하네ㅠㅠㅠ
// 추가 - 나무 나이별로 묶어서 계산하기도 하네... 이게 훨씬 빠르네ㅠㅠㅠㅠ

public class BJ16235_나무재테크_재 {
    static int N, M, K;
    static int[][] map, nutrientMap;
    static PriorityQueue<Tree> trees;
    static LinkedList<Tree> dieTrees;
    static int[] di = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dj = {-1, 0, 1, 1, 1, 0, -1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        nutrientMap = new int[N][N];
        trees = new PriorityQueue<>();
        dieTrees = new LinkedList<>();
        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(in.readLine());
            for(int j = 0 ; j < N ; j++) {
                map[i][j] = 5;
                nutrientMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(in.readLine());
            int posI = Integer.parseInt(st.nextToken()) - 1;
            int posJ = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            trees.offer(new Tree(posI, posJ, age));
        }

        for(int i = 0 ; i < K ; i++) {
            spring();
            summer();
            fall();
            winter();
            if(trees.size() == 0) break;
        }

        System.out.println(trees.size());
    }

    private static void spring() {
        PriorityQueue<Tree> tmpTrees = new PriorityQueue<>();
        dieTrees.clear();
        while(!trees.isEmpty()) {
            Tree tree = trees.poll();
            if(map[tree.i][tree.j] < tree.age) {
                dieTrees.offer(tree);
            } else {
                map[tree.i][tree.j] -= tree.age;
                tmpTrees.offer(new Tree(tree.i, tree.j, tree.age + 1));
            }
        }
        trees = tmpTrees;
    }

    private static void summer() {
        for(Tree tree : dieTrees) {
            map[tree.i][tree.j] += tree.age / 2;
        }
    }

    private static void fall() {
        PriorityQueue<Tree> tmpTrees = new PriorityQueue<>();
        for(Tree tree : trees) {
            if(tree.age % 5 == 0) {
                for(int d = 0 ; d < 8 ; d++) {
                    int ni = tree.i + di[d];
                    int nj = tree.j + dj[d];
                    if(ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
                    tmpTrees.offer(new Tree(ni, nj, 1));
                }
            }
            tmpTrees.offer(tree);
        }
        trees = tmpTrees;
    }

    private static void winter() {
        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < N ; j++) {
                map[i][j] += nutrientMap[i][j];
            }
        }
    }


    static class Tree implements Comparable<Tree>{
        int i, j, age;
        public Tree(int i, int j, int age) {
            this.i = i;
            this.j = j;
            this.age = age;
        }
        public int compareTo(Tree o) {
            return this.age - o.age;
        }
    }
}
