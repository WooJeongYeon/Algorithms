package programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// 이진트리, 같은 level 같은 y값
public class PRO42892_길찾기게임 {
    int[] preorder, postorder;
    int preIdx, postIdx, N, HEIGHT;
    Queue<Node>[] nodes;
    public int[][] solution(int[][] nodeinfo) {
        int[][] answer = new int[2][];
        N = nodeinfo.length;
        Node[] newNodeInfo = new Node[N];
        preorder = new int[N];
        postorder = new int[N];
        for(int i = 0 ; i < N ; i++) {
            newNodeInfo[i] = new Node(nodeinfo[i][1], nodeinfo[i][0], i + 1);
        }
        // y - x로 정렬
        Arrays.sort(newNodeInfo, (o1, o2) -> {
            if(o1.y == o2.y) return o1.x - o2.x;
            return o2.y - o1.y; // y값이 거꾸로 들어와서 내림차순으로
        });
        int height = 0, lastY = -1;

        // height값 찾기
        for(int i = 0 ; i < N ; i++) {
            if(lastY != newNodeInfo[i].y) {
                height++;
                lastY = newNodeInfo[i].y;
            }
        }

        HEIGHT = height;
        nodes = new Queue[height];
        for(int i = 0 ; i < height ; i++) {
            nodes[i] = new LinkedList<>();
        }

        int yIdx = -1;
        lastY = -1;
        // 높이가 Height인 nodes를 만듬
        for(int i = 0 ; i < N ; i++) {
            if(lastY != newNodeInfo[i].y) {
                nodes[++yIdx].offer(newNodeInfo[i]);
                lastY = newNodeInfo[i].y;
            } else {
                nodes[yIdx].offer(newNodeInfo[i]);
            }
        }

        /*for(int i = 0 ; i < height ; i++) {
            while(!nodes[i].isEmpty()) {
                System.out.print(nodes[i].poll().num + " ");
            }
            System.out.println();
        }*/

        order(0, 100000, 0);

        answer[0] = preorder;
        answer[1] = postorder;
        return answer;
    }

    // 순회
    void order(int left, int right, int height) {
        // 범위 벗어나거나 queue에 요소가 더이상 없으면 리턴
        if(nodes[height].isEmpty() || left > nodes[height].peek().x || nodes[height].peek().x > right) return;
        Node node = nodes[height].poll();
        preorder[preIdx++] = node.num;  // 전위순회
        if(height + 1 < HEIGHT) {
            order(left, node.x - 1, height + 1);  // 왼쪽 서브트리로
            order(node.x + 1, right, height + 1); // 오른쪽 서브트리로
        }
        postorder[postIdx++] = node.num; // 후위순회
    }

    class Node {
        int y, x, num;
        public Node(int y, int x, int num) {
            this.y = y;
            this.x = x;
            this.num = num;
        }
    }
}
