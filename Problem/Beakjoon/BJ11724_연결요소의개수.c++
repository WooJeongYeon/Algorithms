#include <stdio.h>
#define SIZE 1001

int N, M, u, v, cnt = 0;
int edges[SIZE][SIZE];
int edgeCntArr[SIZE] = {0};
bool visited[SIZE] = {false};

void dfs(int v) {
    for(int i = 0 ; i < edgeCntArr[v] ; i++) {
        if(!visited[edges[v][i]]) {
            visited[edges[v][i]] = true;
            dfs(edges[v][i]);
        }
    }
}

int main() {
    scanf("%d %d", &N, &M);

    for(int i = 0 ; i < M ; i++) {
        scanf("%d %d", &u, &v);
        edges[u][edgeCntArr[u]++] = v;
        edges[v][edgeCntArr[v]++] = u;
    }

    for(int i = 1 ; i <= N ; i++) {
        if(!visited[i]) {
            cnt++;
            visited[i] = true;
            dfs(i);
        }
    }

    printf("%d\n", cnt);
}