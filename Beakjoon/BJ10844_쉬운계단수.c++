#include <stdio.h>
#define SIZE 101
#define MAX 1000000000

int main() {
    int dp[101][10] = {0};
    int n, ans = 0;

    for(int i = 1 ; i < 10 ; i++) {
        dp[1][i] = 1;
    }

    scanf("%d", &n);

    for(int i = 2 ; i <= n ; i++) {
        for(int j = 0 ; j < 10 ; j++) {
            if(j - 1 >= 0)
                dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % MAX;
            if(j + 1 < 10)
                dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % MAX;
        }
    }

    for(int i = 0 ; i < 10 ; i++) {
        ans = (ans + dp[n][i]) % MAX;
    }

    printf("%d\n", ans);

    return 0;
}