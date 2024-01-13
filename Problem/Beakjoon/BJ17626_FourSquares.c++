#include <stdio.h>
#include <math.h>
#define SIZE 230

int arr[SIZE];
int N, ans = 4, max;

void comb(int start, int cnt, int sum) {
    if(cnt >= ans) 
        return;
    for(int i = start ; i > 0 ; i--) {
        if(sum + arr[i] < N) {
            comb(i, cnt + 1, sum + arr[i]);
        } else if(sum + arr[i] == N) {
            ans = ans > cnt ? cnt : ans;
        }
    }
}

int main() {
    for(int i = 0 ; i < SIZE ; i++) {
        arr[i] = i * i;
    }
    scanf("%d", &N);

    max = sqrt(N);

    comb(max, 1, 0);

    printf("%d\n", ans);

    return 0;
}