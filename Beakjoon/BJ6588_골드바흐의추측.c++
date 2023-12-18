// 이렇게 하면 왜 시간초과가 나지 않을까? 2번에서 100만 * 최악으로 50만 하면 넘어갈텐데..?
#include <stdio.h>
#define SIZE 1000001

int N;
bool isComp[SIZE] = {false};  // composite number - 합성수
int arr[SIZE][2]; // 작은수 - 큰수

int main() {
    // 1. 소수 구하기
    for(int i = 2 ; i <= 1000 ; i++) {
        if(isComp[i])
            continue;
        for(int j = i * i; j < SIZE ; j += i) {
            isComp[j] = true;
        }
    }
    isComp[1] = true;

    // 2. 각 짝수에 대한 결과 구하기
    for(int i = 6 ; i < SIZE ; i += 2) {
        int now = 3;
        while((now < i - now) && (isComp[now] || isComp[i - now])) {
            now += 2;
        }
        if(!isComp[now] && !isComp[i - now]) {
            arr[i][0] = now;
            arr[i][1] = i - now;
        } else {
            arr[i][0] = -1;
        }
    }

    // 3. 결과
    while(true) {
        scanf("%d", &N);
        if(N == 0)
            break;

        if(arr[N][0] == -1)
            printf("Goldbach's conjecture is wrong.\n");
        else
            printf("%d = %d + %d\n", N, arr[N][0], arr[N][1]);
    }

    return 0;
}