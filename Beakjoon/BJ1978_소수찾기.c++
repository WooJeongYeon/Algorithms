#include <stdio.h>
#include <math.h>

int main() {
    int N, cnt = 0, x;
    bool isNotPrime[1001] = {false};

    scanf("%d", &N);

    int max = sqrt(1000);

    for(int i = 2 ; i <= max ; i++) {
        if(isNotPrime[i])
            continue;
        for(int j = i ; i * j <= 1000 ; j++) {
            isNotPrime[i * j] = true;
        }
    }

    isNotPrime[1] = true;

    for(int i = 0 ; i < N ; i++) {
        scanf("%d", &x);
        if(!isNotPrime[x])
            cnt++;
    }

    printf("%d", cnt);

    return 0;
}