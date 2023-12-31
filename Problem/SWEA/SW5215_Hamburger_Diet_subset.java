package day0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * Date : 210809
 */
public class SW5215_Hamburger_Diet_subset {
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
			chooseHbj(0, 0, 0);			// 가장 높은 점수를 저장하기 위해 재귀로 조합을 함
			System.out.println("#" + tc + " " +maxScore);
		}
	}
	// 햄버거의 재료들을 선택하는 메소드 : 부분집합으로 구현!
	// idx의 역할을 명확히 해야할듯
	static void chooseHbj(int idx, int totalScore, int totalKcal) {	
		if(totalKcal > maxKcal)	// 제한 칼로리를 넘어버리면 리턴
			return;
		if(totalScore > maxScore && idx <= num) {	// 답이고, idx가 num 이하이면 
			maxScore = totalScore;
//			return; // 여기서 리턴해줘버리니까 이전에 비해 max값 찾은 이후로는 그 뒤의 재료들은 검사 안함... 40분썼나..ㅠㅠㅠ
		}
		if(idx == num)	// 이부분에서도 애먹고... idx가 num - 1일때 값을 더하고 그 다음 재귀에서 idx가 num이면 답 체크 안하고 리턴해버렸엇음... 
			return;
		chooseHbj(idx + 1, totalScore + score[idx], totalKcal + kcal[idx]);	// 해당 재료를 포함시킴
		chooseHbj(idx + 1, totalScore, totalKcal);		// 해당 재료를 포함시키지 않음
	}
}
