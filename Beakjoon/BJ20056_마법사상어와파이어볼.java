import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * Date : 2021.10.30
 * Level : BaekJoon Gold 5
 * Difficulty : 갠찬 
 * Time : 1시간 30분
 * URL : https://www.acmicpc.net/problem/20056
 * Select1 : 파이어볼 위치 옮기는거 어케 처리해줄지 
 * 		-> 큐에 객체로 저장도 하고 + map에도 저장하는걸로(한쪽만 하고 싶지만 같은 위치의 파이어볼때문에 번거롭)
 * 		-> map 각각을 list로 만들까?(선택) String으로 만들까?
 * Thinking : map을 list 2차원 배열로 만들자! 큐에서 작업 - map에서 작업 반복
 * 		1. 파이어볼 이동 - 큐에 있는 파이어볼 전부 이동
 * 		2. 파이어볼 합치고 쪼개기
 * 			- map의 각각의 list들에 대해 
 * 			- 질량이 0이면 list clear하고 패스
 * 			- 쪼갠 파이어볼들 큐에 넣어줌
 * 		3. 1 - 2번을 K번 반복
 * 		4. 다 끝나고 큐에 남아있는 파이어볼들 총 질량 리턴
 * Method : 구현
 * Error1 : 파이어볼 1개일 때 다시 queue에 넣어야 함 
 * Error2 : 계속 헤멨는데 뭐가 틀렸는지ㅠㅠㅠㅠㅠ 진자 이것때매 디버깅만 40분 넘게 한듯. 1개일 때 map배열 list 클리어 안해줌ㅠㅠ
 * Plus1 : 이걸 map 콜렉션 사용해서도 하네 오...
 */
public class BJ20056_마법사상어와파이어볼 {
	static int N, M, K;
	static List<FireBall>[][] map;			// 이차원 list 배열
	static Queue<FireBall> queue;
	static int[] di = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dj = {0, 1, 1, 1, 0, -1, -1, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new ArrayList[N][N];
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = new ArrayList<FireBall>();
			}
		}
		queue = new LinkedList<>();
		for(int k = 0 ; k < M ; k++) {
			st = new StringTokenizer(in.readLine(), " ");
			int i = Integer.parseInt(st.nextToken()) - 1;
			int j = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			queue.offer(new FireBall(i, j, m, v, d));
		}
		System.out.println(magic());
	}
	
	static int magic() {			// 파이어볼~~
		int time = 0;
		int sum = 0;				// queue에 남아있는게 없으면 0 반환되게
		while(!queue.isEmpty()) {	
			if(time == K) {			// K번 반복하면 총질량 반환되게
				sum = getMassSum();
				break;
			}
			moveFireBall();
			print();
			makeFireBall();
			
			time++;
		}
		return sum;					
	}
	
	static void moveFireBall() {		// 파이어볼 이동
		while(!queue.isEmpty()) {
			FireBall now = queue.poll();
			now.setI(now.i + di[now.d] * now.v);
			now.setJ(now.j + dj[now.d] * now.v);
			map[now.i][now.j].add(now);
		}
	}
	
	static void makeFireBall() {		// 파이어볼 합치고 쪼개기
		for(int i = 0 ; i < N ; i++) {	// 모든 2차원 배열 칸에 대해
			for(int j = 0 ; j < N ; j++) {
				int size = map[i][j].size();
				if(size == 0) continue;			// 파이어볼 없으면 패스
				if(size == 1) {					// 파이어볼 1개면 맵 클리어해주고 큐에 다시 넣고 패스
					queue.offer(map[i][j].get(0));
					map[i][j].clear();
					continue;
				}
				
				int mass = 0;
				int velocity = 0;
				boolean isDSame = true;			// 모든 방향이 짝수이던지 홀수이던지를 저장
				int firstD = map[i][j].get(0).d % 2;	
				for(FireBall fire : map[i][j]) {
					mass += fire.m;
					velocity += fire.v;
					if(firstD != fire.d % 2) {
						isDSame = false;
					}
				}
				mass /= 5;
				velocity /= size;
				
				map[i][j].clear();
				if(mass == 0) continue;			// 질량 0이면 패스
				
				int dir = isDSame ? 0 : 1;		
				for(int d = dir ; d < 8 ; d += 2) {		// 방향설정
					queue.offer(new FireBall(i, j, mass, velocity, d));
				}
			}
		}
	}
	
	static int getMassSum() {			// 총 질량 반환
		int sum = 0;
		while(!queue.isEmpty()) {
			sum += queue.poll().m;
		}
		return sum;
	}
	
	static void print() {
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				System.out.print(map[i][j].size() + " ");
			}
			System.out.println();
		}
		System.out.println("----------------------------");
	}
	
	static class FireBall {
		int i, j, m, v, d;
		public FireBall(int i, int j, int m, int v, int d) {
			setI(i);
			setJ(j);
			this.m = m;
			this.v = v;
			this.d = d;
		}
		public void setI(int i) {		// 0번 - N-1번이 이어지도록 객체 내에서 셋팅
			this.i = i < 0 ? (i + 1000 * N) % N : i % N;
		}
		public void setJ(int j) {
			this.j = j < 0 ? (j + 1000 * N) % N : j % N;
		}
	}
}
