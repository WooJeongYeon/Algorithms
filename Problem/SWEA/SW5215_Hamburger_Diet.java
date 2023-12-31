package day0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210809
 */
public class SW5215_Hamburger_Diet {
	static int TC;		// 테스트 케이스
	static int num;		// 재료 수
	static int maxKcal, maxScore;	// 제한 칼로리, 최대 점수
	static int[] kcal, score;		// 각 재료의 칼로리와 점수를 저장 
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));	
		TC = Integer.parseInt(in.readLine());	// TC를 입력받음
		for(int tc = 1 ; tc <= TC ; tc++) {		// TC번 수행
			StringTokenizer st = new StringTokenizer(in.readLine());	// StringTokenizer을 이용해 한 줄을 가져와서 분리
			num = Integer.parseInt(st.nextToken());		// 재료 수를 입력받음
			maxKcal = Integer.parseInt(st.nextToken());	// 제한 칼로리를 입력받음
			maxScore = 0;			// 현재 최대 점수는 0으로 초기화
			kcal = new int[num];	// 각 재료의 정보 배열 초기화
			score = new int[num];
			for(int i = 0 ; i < num ; i++) {	// 정보들 입력받음
				st = new StringTokenizer(in.readLine());
				score[i] = Integer.parseInt(st.nextToken());
				kcal[i] = Integer.parseInt(st.nextToken());
			}
			chooseHbj(0, 0, 0, 0);			// 가장 높은 점수를 저장하기 위해 재귀로 조합을 함
			System.out.println("#" + tc + " " +maxScore);
		}
	}
	// 햄버거의 재료들을 선택하는 메소드 - 현재 자리수, 시작 인덱스, 현재까지의 총점수, 현재까지의 총칼로리 
	static void chooseHbj(int idx, int start, int totalScore, int totalKcal) {	
		for(int i = start ; i < num ; i++) {		// 모든 재료에 대해 반복(고려)
			// 현재 재료 선택X
			if(totalKcal + kcal[i] > maxKcal)		// 만약 현재 칼로리를 더했을 때, 제한 칼로리를 넘어간다면
				continue;							// 현재 재료를 더하지 말고 다음 재료를 검사하기 위해 continue
			// 현재 재료 선택O
			chooseHbj(idx + 1, i + 1, totalScore + score[i], totalKcal + kcal[i]); // 현재 자리수, 시작 인덱스를 다음으로, 총점수 총칼로리에 현재 재료의 값만큼 추가 
		}
		if(maxScore < totalScore)	// 현재 점수가 여태까지의 최고 점수보다 높다면 
			maxScore = totalScore;	// 새로 저장해줌
	}
}
