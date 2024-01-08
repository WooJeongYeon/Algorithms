#include <stdio.h>

int main() {
    int N, len = 0, now, start, sum, ans = 0;
    scanf("%d", &N);
    now = N;
    while(now > 0) {
        len++;
        now /= 10;
    }
    start = N - 9 * len;

    for(int i = start ; i <= N ; i++) {
        sum = 0;
        now = i;
        while(now > 0) {
            sum += (now % 10);
            now /= 10;
        }
        if((i + sum) == N) {
            ans = i;
            break;
        }
    }
    
    printf("%d\n", ans);

    return 0;
}