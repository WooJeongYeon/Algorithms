package day0814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * Date : 210814
 */
public class SW5356_SaySelo {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); // 결과를 저장할 StringBuilder
		final int strNum = 5; // 입력받는 단어 개수
		String[] words = new String[strNum]; // 입력받은 단어
		final int TC = Integer.parseInt(in.readLine()); // 테스트케이스 수
		for (int tc = 1; tc <= TC; tc++) { // TC만큼 반복
			sb.append("#" + tc + " ");
			int maxLen = Integer.MIN_VALUE; // 최댓값 저장
			for (int i = 0; i < strNum; i++) { // 단어 개수만큼 반복
				words[i] = in.readLine();
				if (words[i].length() > maxLen)
					maxLen = words[i].length(); // 가장 긴 단어의 길이를 저장
			}
			for (int i = 0; i < maxLen; i++) { // 왼쪽에서 오른쪽으로 이동하면서 위에서 아래로 검사(단어의 알파벳 추가)
				for (int j = 0; j < strNum; j++) { // 단어 개수만큼 반복
					if (words[j].length() <= i)
						continue; // 해당 단어의 길이가 검사하는 자리보다 작다면 continue(추가할 알파벳이 없으므로)
					sb.append(words[j].charAt(i)); // 해당 단어의 검사 자리 알파벳 추가
				}
			}
			sb.append("\n");
		}
		System.out.println(sb); // 결과 출력
	}
}
