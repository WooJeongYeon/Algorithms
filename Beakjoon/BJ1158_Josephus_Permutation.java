package day0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
/*
 * Date : 210810
 */
public class BJ1158_Josephus_Permutation {
	public static void main(String[] args) throws IOException {
		LinkedList<Integer> list = new LinkedList<Integer>();	// 남아있는 사람을 가지고있는 LinkedList
		StringBuilder sb = new StringBuilder();		// 결과를 만들 StringBuilder
		sb.append("<");	
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());	// N명의 사람에 대해
		int K = Integer.parseInt(st.nextToken());	// K번째 사람을 제거
		for(int i = 1 ; i <= N ; i++) {				// 모든 사람의 번호를 리스트에 추가
			list.add(i);
		}
		int i = 0;
		while(list.size() > 0) {				// 리스트에 남아있는 사람이 없을 때까지 반복
			i = (i + K - 1) % list.size();		// 원형 리스트처럼 구현되도록 인덱스가 리스트 크기를 넘어가면 나머지연산을 해줌
			sb.append(list.remove(i) + ", ");	// 리스트에서 K번째 사람 제거 후 결과에 추가
		}
		sb.setLength(sb.length() - 2);			// 끝에 붙언 ,와 공백을 없앰
		sb.append(">");
		System.out.println(sb);					// 결과 출력
	}
}
