package day0820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;
/*
 * Date : 210820
 */
public class BJ17135_CastleDefense {
	static final int archerNum = 3;
	static LinkedList<Point> enemys;
	static boolean[] selected;
	static int[] archers;
	static int killEnemyMax;
	static int N, M, D;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		enemys = new LinkedList<Point>();
		selected = new boolean[M];
		archers = new int[archerNum];
		killEnemyMax = Integer.MIN_VALUE;
		int now;
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < M ; j++) {
				now = Integer.parseInt(st.nextToken());
				if(now == 1) {
					enemys.add(new Point(i, j));
				}
			}
		}
//		for(Point p : enemys) {
//			System.out.println(p.i + " " + p.j);
//		}
		comb(0, 0);
		
		System.out.println(killEnemyMax);
	}
	static void comb(int idx, int cnt) {
		if(cnt == archerNum) {				// 아처 위치가 모두 선택되면
			int here = 0;
			for(int i = 0 ; i < M ; i++) {
				if(selected[i]) archers[here++] = i;
			}
			int killEnemy = simul();		// 시뮬 돌려서 죽인 적 수 얻어내기
			if(killEnemy > killEnemyMax) killEnemyMax = killEnemy;
			return;
		}
		if(idx == M) return;
		
		selected[idx] = true;
		comb(idx + 1, cnt + 1);
		selected[idx] = false;
		comb(idx + 1, cnt);
	}
	static int simul() {					// 시뮬 돌려
		int killNum = 0;	
		LinkedList<Point> copyEnemys = new LinkedList<Point>();
		Set<Point> set = new HashSet<Point>();
		for(Point e : enemys) {				
			copyEnemys.add(new Point(e.i, e.j));
		}
//		System.out.println("아처 선택 : " + archers[0] + " " + archers[1] + " " + archers[2] + " ");
		while(true) {						// 적이 없을 떄까지
			for(int i = 0 ; i < archerNum ; i++) {		// 모든 아처에 대해 죽일 적 고르기
				int minD = D;
				Point choose = null;
				int leftJ = M;
				for(Point e : copyEnemys) {				// 가장 가깝고 왼쪽의 적을 선택
					int d = Math.abs(e.i - N) + Math.abs(e.j - archers[i]);
					if(minD >= d) {
						if(minD > d) {
							minD = d;
						}
						else if(minD == d && leftJ <= e.j) 
							continue;
                        choose = e;
						leftJ = e.j;											
//						System.out.println(i + " " + d);
					}
				}
				if(choose != null) set.add(choose);		// set에 넣어서 중복 제거
//				System.out.println("set 크키 : " + set.size());
			}
			killNum += set.size();
			for(Point e : set) {						// set에 들어간 적들을 죽임
				copyEnemys.remove(e);
			}
			set.clear();
			int idx = 0;
			while(!copyEnemys.isEmpty()) {				// 모든 적에 대해 밑으로 이동시켜줌
//				System.out.println(idx + " " + copyEnemys.size());
				copyEnemys.get(idx).i++;
				if(copyEnemys.get(idx).i == N) {		// 밑에 도달했다면 적 제거
					copyEnemys.remove(copyEnemys.get(idx));
				}
				else idx++;
				if(idx == copyEnemys.size()) break;		
			}
//			System.out.println("남은 적 : " + copyEnemys.size());
			if(copyEnemys.isEmpty()) break;
		}
//		System.out.println("kill : " + killNum);
		return killNum;
	}
	static class Point{
		int i;
		int j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
		public boolean equals(Object a) {				// set을 사용하기 위해 구현
	        Point obj = (Point) a;
	        return (this.i == obj.i && this.j == obj.j);
	    }

	    @Override
	    public int hashCode() {							// set을 사용하기 위해 구현
	        return (i + "" + j).hashCode();
	    }
	}
}
