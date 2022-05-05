package programmers;
/*
 * Date : 2022.05.05
 * Level : Programmers level 3
 * Difficulty : 중
 * Why : 내가 어렵게 푼거같은데...
 * Time : 1h 30m
 * Select : op랑 num 모두 저장해야되서 링크드리스트 직접 만들어서 Node로 풀어야겠따 생각했다ㅠㅠㅠ
 * Thinking : 귀찮으니까 +-* 모두에 대해 3!번 순열돌자(있는애만 체크해도 되는데... 기차나..)
 *          - 3번 * 노드 한바퀴 다 돌면서 해당 연산자인 애들 계산!
 * Method : LinkedList, Permutation
 * Error1 : 갱신된 애들은 다음에 또 계산할 수 있는지 봐야 함
 * Result : 노드로 풀다가 나중에 ArrayList로 풀어도 되겠는데? 했지만 그냥 풀었다
 * Plus1 : 생각보다 시간이 오래걸리네ㅠㅠㅠ list랑 푸는게 왜 차이날까?
 */
public class PRO67257_수식최대화 {
    Node node, cNode;
    String opStr = "+-*";
    boolean[] visited;
    long ans;
    final int SIZE = 3;
    public long solution(String expression) {
        changeNode(expression);
        visited = new boolean[SIZE];
        perm(0, "");
        return ans;
    }
    void changeNode(String ex) {
        String[] numArr = ex.split("[^0-9]");
        String[] opArr = ex.split("[0-9]+");
        node = setNode(0, numArr, opArr);
    }
    Node setNode(int idx, String[] numArr, String[] opArr) {        // 노드 초기화(재귀)
        if(idx == numArr.length) return null;
        char op = 0;
        if(idx + 1 < opArr.length)
            op = opArr[idx + 1].charAt(0);
        Node node = new Node(Integer.parseInt(numArr[idx]), op);
        node.next = setNode(idx + 1, numArr, opArr);
        return node;
    }
    void perm(int idx, String s) {
        if(idx == SIZE) {
            cNode = copyNode(node);
            for(int i = 0 ; i < SIZE ; i++) {
                calNode(s.charAt(i));
            }
            ans = Math.max(ans, Math.abs(cNode.val));
            return;
        }
        for(int i = 0 ; i < SIZE ; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            perm(idx + 1, s + opStr.charAt(i));
            visited[i] = false;
        }
    }
    Node copyNode(Node nowNode) {       // 노드 복사(재귀)
        if(nowNode == null) return null;
        Node newNode = new Node(nowNode.val, nowNode.op);
        newNode.next = copyNode(nowNode.next);
        return newNode;
    }

    void calNode(char c) {
        Node now = cNode;
        while(now != null) {
            if(now.op == c) {
                now.cal();
                continue;       // 갱신된 애들은 다음에 또 계산할 수 있는지 봐야 함
            }
            now = now.next;
        }
    }

    class Node {
        long val;
        char op;
        Node next;
        public Node(long val, char op) {
            this.val = val;
            this.op = op;
        }
        void cal() {        // 계산
            switch(op) {
                case '-' :
                    val -= next.val;
                    break;
                case '+' :
                    val += next.val;
                    break;
                case '*' :
                    val *= next.val;
                    break;
            }
            op = next.op;
            next = next.next;       // 계산 된 노드 없애줌
        }
    }
}
