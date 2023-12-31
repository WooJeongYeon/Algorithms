// dfs 코드 다른 사람들꺼 봤는데 이해 안감...

#include <stdio.h>
#define MAX 1000001
#define SIZE 1000001
#define min(x, y) x > y ? y : x

int dfs(int n) {
    if(n <= 1)
        return 0;

    int a = dfs(n / 3) + n % 3 + 1;
    int b = dfs(n / 2) + n % 2 + 1;

    return min(a, b);
}


int N;
int memo[SIZE];

void dp() {
    for(int i = 1 ; i < N ; i++) {
        memo[i + 1] = min(memo[i + 1], memo[i] + 1);
        if(i * 2 <= N)
            memo[i * 2] = min(memo[i * 2], memo[i] + 1);
        if(i * 3 <= N)
            memo[i * 3] = min(memo[i * 3], memo[i] + 1);
    }
}

int main() {
    scanf("%d", &N);

    for(int i = 0 ; i <= N ; i++) {
        memo[i] = MAX;
    }
    memo[1] = 0;

    // dp();

    // printf("%d\n", memo[N]);

    printf("%d\n", dfs(N));

    return 0;
}