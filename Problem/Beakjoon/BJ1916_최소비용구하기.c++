#include <stdio.h>
#define SIZE 1001
#define INF 100000001

struct Node {
    int e;
    int w;
    Node* next = NULL;
};

int N, M, start, end, s, e, w, nodeCnt = 0, minV, minVal, ans;
Node* edges[SIZE];
Node* now;
Node node[100000];
bool visited[SIZE] = {false};
int minArr[SIZE];


int main() {
    for(int i = 1 ; i < SIZE ; i++) {
        minArr[i] = INF;
    }

    scanf("%d\n%d", &N, &M);
    for(int i = 0 ; i < M ; i++) {
        scanf("%d %d %d", &s, &e, &w);
        node[nodeCnt].e = e;
        node[nodeCnt].w = w;
        node[nodeCnt].next = edges[s];
        edges[s] = &node[nodeCnt++];
    }
    scanf("%d %d", &start, &end);

    minArr[start] = 0;

    for(int i = 1 ; i <= N ; i++) {
        minV = start;
        minVal = INF;
        
        for(int j = 1 ; j <= N ; j++) {
            if(!visited[j] && minArr[j] < minVal) {
                minV = j;
                minVal = minArr[j];
            }
        }

        if(minV == end) {
            break;
        }
        visited[minV] = true;
        now = edges[minV];

        while(now != NULL) {
            minArr[now->e] = minArr[now->e] < (minArr[minV] + now->w) ? minArr[now->e] : (minArr[minV] + now->w);
            now = now->next;
        }
    }

    printf("%d\n", minArr[end]);

}