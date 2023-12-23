#include <stdio.h>

int gcp(int a, int b) {
    int tmp;
    while(b > 0) {
        tmp = a % b;
        a = b;
        b = tmp;
    }
    return a;
}

int main() {
    int t, n;
    long long int sum = 0;
    int arr[100];

    scanf("%d", &t);
    for(int tc = 0 ; tc < t ; tc++) {
        sum = 0;
        scanf("%d", &n);
        for(int i = 0 ; i < n ; i++) {
            scanf("%d", &arr[i]);
        }
        for(int j = 0 ; j < n - 1 ; j++) {
            for(int k = j + 1 ; k < n ; k++) {
                sum += gcp(arr[j], arr[k]);
            }
        }
        printf("%lld\n", sum);
    }

    return 0;
}