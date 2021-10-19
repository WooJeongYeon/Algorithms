package day0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210816
 */
public class BJ17827_SnailList {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());
		final int N = Integer.parseInt(st.nextToken());	// 노드 개수
		final int M = Integer.parseInt(st.nextToken());	// 질문 횟수
		final int V = Integer.parseInt(st.nextToken());	// N번 노드(1번부터 번호를 매길 때, 마지막 노드)가 가리키는 노드의 번호
		int[] list = new int[N];		
		st = new StringTokenizer(in.readLine());
		for(int i = 0 ; i < N ; i++) {	// N개의 정수를 저장
			list[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0 ; i < M ; i++) {	// M개의 질문에 대해 
			int k = Integer.parseInt(in.readLine());	// K번째 노드의 값을 찾기
			int idx;
			if(k < N) idx = k; 							// N보다 작으면 인덱스는 K로
			else {										// N과 같거나 크면 달팽이 사이클을 돌음
				int snailSize = N - V + 1;				// 달팽이 사이클의 크기
				idx = N - snailSize + (k - N + snailSize) % snailSize;	// 달팽이 사이클에 연결되지 않은 노드 개수 + 달팽이 사이클에서 K의 위치 -> 전체 리스트의 인덱스
			}
			sb.append(list[idx] + "\n");	// 결과를 stringbuilder에 추가
		}
		System.out.println(sb);				// 결과 출력
	}
}
