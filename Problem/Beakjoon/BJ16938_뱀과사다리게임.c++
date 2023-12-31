#include <stdio.h>
#define SIZE 101
#define INF 1000

int N, M, x, y;
int map[SIZE];
int memo[SIZE];

void dfs(int pos, int cnt) {
    memo[pos] = cnt;
    for(int i = 6 ; i >= 1 ; i--) {
        if(pos + i > 100 || (cnt + 1) >= memo[map[pos + i]]) {
            continue;
        }
        dfs(map[pos + i], cnt + 1);
    }
}

int main() {
    for(int i = 1 ; i < SIZE ; i++) {
        map[i] = i;
        memo[i] = INF;
    }

    scanf("%d %d", &N, &M);
    for(int i = 0 ; i < N + M ; i++) {
        scanf("%d %d", &x, &y);
        map[x] = y;
    }

    dfs(1, 0);

    printf("%d", memo[100]);

    return 0;
}