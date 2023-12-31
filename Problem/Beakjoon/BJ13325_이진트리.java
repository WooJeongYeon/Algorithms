import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 2021.12.15
 * Level : BaekJoon Gold 4
 * Difficulty : 중간
 * URL : https://www.acmicpc.net/problem/13325
 * Select1 : Node 클래스로 링크드리스트처럼 할까 하다가 저장하기 어려워서 포기
 * Select2 : 배열에 엣지 저장해서 순회느낌으로... 배열 크기는 2 ^ (K + 1)까지
 * Select1 : 인덱스 어케할까 하다가 2부터 시작하면 왼쪽자식은 idx * 2, 오른쪽은 idx * 2 + 1로 됨
 * Thinking : 배열 사용해 양쪽 자식 길이랑 합쳐 비교해 큰 쪽으로 맞춰서 edge에 가중치 저장되도록
 * 			- 다 하고 edge배열의 총 합 반환
 * Method : 재귀
 * Error1 : 재귀에서 반환 잘못함 -> 큰 길이 반환해줘야 함
 * Result : 이진트리라해서 노드 가중치 생각했는데 나중에 보니 엣지 가중치여서 이거 어떻게 처리할까랑 재귀 어케할지 생각하는게 어려웠음..
 * Plus : 재귀 내에서 edge를 변경시키지 말고, ans를 따로 빼서 재귀 하면서 계속 더해줘도 되겠네 
 */
public class BJ13325_이진트리 {
	static int k;
	static long[] edge;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(in.readLine());
		edge = new long[1 << (k + 1)];
		String[] strArr = in.readLine().split(" ");
		int idx = 2;
		for(String s : strArr) {
			edge[idx++] = Long.parseLong(s);
		}
		
		getLen(2);
		
		long ans = 0;
		for(long n : edge) {
			ans += n;
		}
		System.out.println(ans);
	}
	private static long getLen(int start) {
		if(start >= edge.length) return 0;
		long len1 = getLen(start * 2) + edge[start];
		long len2 = getLen(start * 2 + 2) + edge[start + 1];
		long sub = Math.abs(len1 - len2);
		if(len1 > len2) {
			edge[start + 1] += sub;
		} else edge[start] += sub;
		return Math.max(len1, len2);
	}
}
