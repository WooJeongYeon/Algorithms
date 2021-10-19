package day0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210812
 */
public class SW6808_CardGame_BitMask {
	static int[] myCards, yourCards, cards;
	static int win, lose;
	static final int SIZE = 9;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		final int TC = Integer.parseInt(in.readLine());	// 테케 수
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();	// 결과를 모아서 저장할 StringBuilder
		myCards = new int[SIZE];		// 나의 카드 순서 저장
		yourCards = new int[SIZE];		// 인영이의 카드 순서 저장
		cards = new int[SIZE];			// 인영이가 뽑을 수 있는 카드들 저장
		for(int tc = 1 ; tc <= TC ; tc++) {	// TC 수만큼 반복
			st = new StringTokenizer(in.readLine());
			int flag = 0;				// 선택된 카드 표시
			win = 0; lose = 0;				// 승리, 패배 횟수 저장
			for(int i = 0 ; i < SIZE ; i++) {			// 나의 카드 입력받음
				myCards[i] = Integer.parseInt(st.nextToken());
				flag = flag | 1 << (myCards[i] - 1);	// 오른쪽부터 시작해서 해당 비트(0 ~ 17)에 선택됬음을 표시
			}
			int idx = 0;				// 내가 뽑지 않은 카드를 저장
			for(int i = 0 ; i < SIZE * 2 ; i++) {
				if((flag & 1 << i) != 0) continue;		// 내가 뽑은 카드라면 패스
				cards[idx++] = i + 1;	// 뽑을 수 있는 카드 저장
			}
			perm(0, 0);					// 인영이의 카드뽑기
			sb.append("#" + tc + " " + win + " " + lose + "\n"); //결과 추가
		}
		System.out.println(sb);	// 출력
	}
	static void perm(int idx, int flag) {		// 여태까지 뽑은 카드수, 뽑았는지 여부를 저장한 flag
		if(idx == SIZE) {		// 모든 카드를 뽑았다면
			int myScore = 0, yourScore = 0;
			for(int i = 0 ; i < SIZE ; i++) {	// 각 라운드마다 이긴 사람에게 점수를 더해줌
				int score = myCards[i] + yourCards[i];
				if(myCards[i] > yourCards[i]) myScore += score;
				else if(myCards[i] < yourCards[i]) yourScore += score;
			}
			if(myScore > yourScore) win++;		// 총 점수가 내가 더 높으면 승리
			else if(myScore < yourScore) lose++;	// 낮으면 패배
			return;		
		}
		for(int i = 0 ; i < SIZE ; i++) {		// 모든 카드에 대해(순열이므로)
			if((flag & 1 << i) != 0) continue;	// 뽑힌 카드라면 다음으로
			yourCards[idx] = cards[i];			// 카드를 선택
			perm(idx + 1, flag | 1 << i);		// 재귀함수 호출(다음 카드 뽑으러, 이번차례 카드를 뽑았음을 표시)
		}
	}
}
