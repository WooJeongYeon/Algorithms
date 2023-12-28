// 이분게 좀 신박(음수 저장해서 높이 조절) - https://www.acmicpc.net/source/45589630

#include <stdio.h>
#define SIZE 1000001

int parents[SIZE];

void make(int N) {
    for(int i = 1 ; i <= N ; i++) {
        parents[i] = i;
    }
}

int find(int n) {
    if(parents[n] == n) {
        return n;
    }

    return parents[n] = find(parents[n]);
}

void unionSet(int a, int b) {
    int rootA = find(a);
    int rootB = find(b);

    if(rootA != rootB)
        parents[rootB] = rootA;
}

int main() {
    int n, m;
    int cmd, a, b;
    
    scanf("%d %d", &n, &m);

    make(n);

    for(int tc = 0 ; tc < m ; tc++) {
        scanf("%d %d %d", &cmd, &a, &b);
        switch(cmd) {
            case 0 :
                unionSet(a, b);
                break;
            case 1 :
                if(find(a) == find(b)) {
                    printf("YES\n"); 
                } else {
                    printf("NO\n");
                }
                break;
        }
    }

    return 0;
}