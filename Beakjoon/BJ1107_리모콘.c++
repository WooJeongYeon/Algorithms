#include <stdio.h>
#include <stdlib.h>

int N, M, min, num;
bool isBroken[10] = {false};

void dfs(int n, int cnt) {
    int dist = abs(N - n) + cnt;
    min = dist < min ? dist : min;
    int newN = n * 10;

    for(int i = 0 ; i < 10 ; i++) {
        if(!isBroken[i] && newN + i < 2 * N)
            dfs(newN + i, cnt + 1);
    }
}

int main() {
    scanf("%d", &N);
    scanf("%d", &M);
    min = abs(N - 100);
    for(int i = 0 ; i < M ; i++) {
        scanf("%d", &num);
        isBroken[num] = true;
    }

    
    if(!isBroken[0])
        min = (N + 1) < min ? (N + 1) : min;

    for(int i = 1 ; i < 10 ; i++) {
        if(!isBroken[i])
            dfs(i, 1);
    }

    printf("%d\n", min);

    return 0;
}