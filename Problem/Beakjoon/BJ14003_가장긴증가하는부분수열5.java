package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 링크드리스트 느낌으로...
// Node가 최대 N개만큼 나오지 않을까?
// Nodes에 해당 길이까지의 부분 수열들이 저장되도록(last로 차례대로 이전으로 가면 부분 수열 출력 가능)
// 전깃줄2랑 완전 같은문제인듯(전깃줄2는 중복이 없는거 차이..?)
// 왜 시간초과..? NlogN 아닌가? -> StringBuilder에 insert 해서 그럼
// 왜 틀려!!!! 90%인가..?
// 주의
//1. 중복값 들어온 경우 처리
//2. 음수 처리 - dp[0]부터 음수 저장
//3. insert 조심(sb.insert -> O(N)의 시간복잡도)

// 오... 다들 다르게 풀었네. 이분탐색 LIS + int배열인 index배열 추가 - 원본배열의 값이 LIS에서 몇번째 인덱스에 저장되는지를 저장 -> len부터 역순으로 순서를 골라감
// <-> 나는 노드배열

public class BJ14003_가장긴증가하는부분수열5 {
    static int N, num, len;
    static int[] dp;
    static Node[] nodes;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        dp = new int[N + 1];
        nodes = new Node[N + 1];
        st = new StringTokenizer(in.readLine());
        dp[0] = Integer.MIN_VALUE;      // 이거땜시... 0보다 적은 값이 처음에 들어온 경우 저장이 안됬네
        for(int i = 1 ; i <= N ; i++) {
            dp[i] = Integer.MIN_VALUE;
            num = Integer.parseInt(st.nextToken());
            if(dp[len] < num) {
                dp[++len] = num;
                nodes[len] = new Node(num, nodes[len - 1]);
            } else {
                int le = 1, ri = len;
                int pos = -1;
                while(le <= ri) {
                    int mid = (le + ri) / 2;
                    if(dp[mid] >= num) { // 중복인 경우, 일로 들어가니까 ㄱㅊ
                        pos = mid;
                        ri = mid - 1;
                    } else {
                        le = mid + 1;
                    }
                }
                if(pos != -1) {  // -1 나올일이 있나...? 없을텐데
                    dp[pos] = num;
                    nodes[pos] = new Node(num, nodes[pos - 1]);
                }
            }
        }

        Node now = nodes[len];
        sb.append(len + "\n");
        int[] numArr = new int[len + 1];
        int idx = len;
        while(now != null) {
//            sb.insert(0, now.num + " ");    // 얘땜시 시간초과 난듯(insert하면 시간복잡도 O(N)이래
            numArr[idx--] = now.num;
            now = now.last;
        }
        for(int i = 1 ; i <= len ; i++) {
            sb.append(numArr[i] + " ");
        }
        System.out.println(sb.toString());
    }
    static class Node {
        int num;
        Node last;
        public Node(int num, Node last) {
            this.num = num;
            this.last = last;
        }
    }
}
