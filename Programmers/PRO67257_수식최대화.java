package programmers;

public class PRO67257_수식최대화 {
    Node node, cNode;
    String opStr = "+-*";
    boolean[] visited;
    long ans;
    final int SIZE = 3;
    public long solution(String expression) {
        changeNode(expression);
        visited = new boolean[SIZE];
        Node now = node;
        perm(0, "");
        return ans;
    }
    void changeNode(String ex) {
        String[] numArr = ex.split("[^0-9]");
        String[] opArr = ex.split("[0-9]+");
        node = setNode(0, numArr, opArr);
    }
    Node setNode(int idx, String[] numArr, String[] opArr) {
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
    Node copyNode(Node nowNode) {
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
                continue;
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
        void cal() {
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
            next = next.next;
        }
    }
}
