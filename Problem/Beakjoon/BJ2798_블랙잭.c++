// 3중 for문으로 끝나네ㅋㅋㅋㅋ
#include <stdio.h>
#define SIZE 100

int N, M, max = 0;
int arr[SIZE];

void comb(int idx, int cnt, int sum) {
    if(cnt == 3) {
        if(sum > max)
            max = sum;
        return;
    }

    for(int i = idx ; i < N ; i++) {
        if((sum + arr[i]) <= M) {
            comb(i + 1, cnt + 1, sum + arr[i]);
        }
    }
}

int main() {
    scanf("%d %d", &N, &M);
    for(int i = 0 ; i < N ; i++) {
        scanf("%d", &arr[i]);
    }

    comb(0, 0, 0);

    printf("%d\n", max);

    return 0;
}