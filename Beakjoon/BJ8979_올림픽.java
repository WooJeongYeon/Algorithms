import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * Date : 210922
 * Thinking : 스코어 클래스 만들어서 compareTo 메소드 구현 -> sort하자. 그리고 K번째까지 몇등인지 찾아가기
 * Method : 
 * Error1 : 정렬이 안돼는디..? 오름차순이 아니라 내림차순 해야함
 * Another : 다른 사람 코드 봤는데 배열에 100 * gold + 10 * silver + 1 * bronze해서 저장했네... 천재다ㅠㅠㅠ
 */
public class BJ8979_올림픽 {
	static int N, K, rank;
	static Score[] scores;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		scores = new Score[N];
		
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(in.readLine());
			int num = Integer.parseInt(st.nextToken());
			int gold = Integer.parseInt(st.nextToken());
			int silver = Integer.parseInt(st.nextToken());
			int bronze = Integer.parseInt(st.nextToken());
			scores[i] = new Score(num, gold, silver, bronze);
		}
		Arrays.sort(scores);
		rank = 1;
		if(K != scores[0].num) {
			for(int i = 1 ; i < N ; i++) {
				if(scores[i - 1].gold != scores[i].gold || scores[i - 1].silver != scores[i].silver 
						|| scores[i - 1].bronze != scores[i].bronze) {
					rank = i + 1;
				}
				if(K == scores[i].num) break;
			}
		}
		
		System.out.println(rank);
	}
	static class Score implements Comparable<Score>{
		int num, gold, silver, bronze;

		public Score(int num, int gold, int silver, int bronze) {
			this.num = num;
			this.gold = gold;
			this.silver = silver;
			this.bronze = bronze;
		}

		@Override
		public int compareTo(Score o) {
			if(this.gold != o.gold) return o.gold - this.gold;
			if(this.silver != o.silver) return o.silver - this.silver;
			return o.bronze - this.bronze;
		}
	}
}
