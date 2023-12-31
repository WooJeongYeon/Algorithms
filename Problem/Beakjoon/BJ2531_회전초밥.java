import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * Date : 2021.10.05
 * Level : BaekJoon 실버1, 골드4 -> 둘다 통과함
 * Thinking)
 * 		1. 처음 k개의 초밥에 대해 cnt와 초밥종류수(size)를 구함(0번부터 시작해 K개)
 * 		2. 0 ~ N - 2까지 0번 초밥을 빼고 k번 초밥을 더하는 식으로 반복(1번 ~ N - 1번부터 시작하도록)
 * 		3. c번 초밥이 세지지 않았다면 1을 더해서 ans 계산
 * 		- 초밥배열 쓰려다 안쓰려다 하다가 안쓰려고 보니 이전값 빼줘야해서 써야했다..
 * Method : 0번 ~ N - 1번부터 시작하면서 k개의 초밥종류 구해서 비교
 * Error1 : SushiCnt 배열의 크기는 d + 1이 되야함(쿠폰번호가 1 ~ d임)
 * Error2 : 틀림 -> 회전초밥이라서 0 ~ N - 1번부터 시작하도록 해줘야 함
 * Error3 : 배열 이름 빼먹고 인덱스 지정함... 진짜 이거땜시 너무 화났다ㅠㅠㅠㅠ 찍어도 모르겠어서. 역시 컴퓨터는 틀리지 않고 내가 틀림ㅜ
 * Plus1 : ans 코드가 반복으로 들어가서 for문에서 while문으로 바꿈
 * Plus2 : 쿠폰을 처음에 넣어버리고 시작하면 더 편하대
 * Plus3 : size가 d이면 바로 종료할 수 있대
 * Result : 분명 생각하면서 했는데... 너무 오늘 힘들고 문제 그만풀고 싶어서 급하게 했다보다. 문제도 잘못읽고, 배열 이름도 잘못쓰고...
 */
public class BJ2531_회전초밥 {
	static int N, d, k, c, n, size, ans;
	static int[] sushi;
	static int[] sushiCnt;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		sushi = new int[N];							// 존재하는 초밥번호 저장
		sushiCnt = new int[d + 1];					// 초밥개수 저장 -> int 배열 초기값 0
		sushiCnt[c]++;								// 쿠폰 초밥은 무조건 먹을 수 있으니 일단 먹어두기!
		size = 1;
//		ans = Integer.MIN_VALUE;					// Max값 저장하기 위해 min값 저장
//		Arrays.fill(sushiCnt, 0);					// 개수 0으로 초기화
		
		for(int i = 0 ; i < N ; i++) {
			sushi[i] = Integer.parseInt(in.readLine());
			if(i < k) {								// 처음 k개에 대해 개수 계산해줌
				sushiCnt[sushi[i]]++;
				if(sushiCnt[sushi[i]] == 1) size++;
			}
		}
		int i = 0;
		while(true) {	
			ans = Integer.max(ans, size);
			
			if(i == N - 1) break;					
			
			sushiCnt[sushi[i]]--;										// i번의 초밥개수 감소
			if(sushiCnt[sushi[i]] == 0) size--;							// 개수가 0되면 현재 종류수 감소
			sushiCnt[sushi[(i + k) % N]]++;								// i + k번의 초밥개수 증가
			if(sushiCnt[sushi[(i + k) % N]] == 1) size++;				// 개수가 1되면 현재 종류수 증가
			i++;
		}
		System.out.println(ans);
	}
}
