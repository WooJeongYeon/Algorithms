import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
/*
 * Date : 2021.12.13
 * Level : BaekJoon Gold 4
 * Difficulty : 중(ㅠㅠㅠ)
 * Why : 시간초과 조심하는 것 때문에...
 * URL : https://www.acmicpc.net/problem/16235
 * Select1 : tree를 한 list에 저장해서 sort하고 처리
 * Thinking : 그냥 시뮬...
 * Method : 시뮬레이션, 시간제한
 * Error1 : 계속 시간초과 남 -> PQ 사용
 * Error2 : 틀렸다 뜸 -> x, y로 입력받는게 행/열임...
 * Error3 : ArrayList -> LinkedList로 바꿈
 * Help : 디버깅 요청해서 2, 3 에러 해결했다ㅠㅠㅠ
 * Result : 시간 계산하는 방법 잘 모르겠다..
 * Plus1 : sort를 처음에 하고 할 필요가 없을 수 있다...!! 
 * 		-> 맨 처음에 내림차순으로 정렬하고, 계속 추가하는 1age 나무들 뒤에 추가하고 뒤에서부터 접근
 * 		-> 정렬 안해도 작은 순으로 접근하게 됨! -> PQ 쓸 필요 없음!
 */
public class BJ16235_나무재테크 {
	static int N, M, K;
	static int[][] map, nutrients;
	static PriorityQueue<Tree> trees;
	static LinkedList<Tree> tmpTrees, dieTrees;		
	static int[] di = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dj = {-1, 0, 1, 1, 1, 0, -1, -1};
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();
		map = new int[N][N];
		nutrients = new int[N][N];
		trees = new PriorityQueue<>();
		tmpTrees = new LinkedList<>();
		dieTrees = new LinkedList<>();
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = 5;
				nutrients[i][j] = in.nextInt();
			}
		}
		for(int i = 0 ; i < M ; i++) {
			int r = in.nextInt() - 1;			
			int c = in.nextInt() - 1;
			int age = in.nextInt();
			trees.offer(new Tree(r, c, age));
		}
		
		for(int k = 0 ; k < K ; k++) {			
			if(trees.size() == 0) break;
			spring();
			summer();
			fall();
			winter();
			move();
		}
		System.out.println(trees.size());
	}
	private static void move() {
		while(!tmpTrees.isEmpty()) {
			trees.offer(tmpTrees.remove(0));
		}
	}
	private static void spring() {
		while(!trees.isEmpty()) {
			Tree tree = trees.poll();
			if(map[tree.i][tree.j] >= tree.age) {
				map[tree.i][tree.j] -= tree.age++;
				tmpTrees.add(tree);
			} else {
				dieTrees.add(tree);
			}
		}
	}
	private static void summer() {
		for(Tree tree : dieTrees) {
			map[tree.i][tree.j] += tree.age / 2;
		}
		dieTrees.clear();
	}
	private static void fall() {
		for(Tree tree : tmpTrees) {
			if(tree.age % 5 != 0) continue;
			for(int d = 0 ; d < 8 ; d++) {
				int ni = tree.i + di[d];
				int nj = tree.j + dj[d];
				if(ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
				trees.offer(new Tree(ni, nj, 1));
			}
		}
	}
	private static void winter() {
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				map[i][j] += nutrients[i][j];
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
