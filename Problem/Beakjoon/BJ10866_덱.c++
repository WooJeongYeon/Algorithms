#include <stdio.h>
#include <string.h>

typedef struct Deque {
    int front = 10000;
    int back = 10000;
    int arr[20001];
} Deque;

bool empty(Deque* deque) {
    return (deque->back - deque->front) == 0;
}

void pushFront(Deque* deque, int x) {
    deque->arr[--(deque->front)] = x;
}

void pushBack(Deque* deque, int x) {
    deque->arr[(deque->back)++] = x;
}

int popFront(Deque* deque) {
    if(empty(deque))
        return -1;
    return deque->arr[(deque->front)++];
}

int popBack(Deque* deque) {
    if(empty(deque))
        return -1;
    return deque->arr[--(deque->back)];
}

int size(Deque* deque) {
    return deque->back - deque->front;
}

int front(Deque* deque) {
    if(empty(deque))
        return -1;
    return deque->arr[(deque->front)];
}

int back(Deque* deque) {
    if(empty(deque))
        return -1;
    return deque->arr[(deque->back) - 1];
}

int main() {
    int N, x;
    char str[11];
    Deque deque;

    scanf("%d\n", &N);
    for(int i = 0 ; i < N ; i++) {
        scanf("%s", str);
        if(strcmp(str, "push_back") == 0) {
            scanf("%d", &x);
            pushBack(&deque, x);
        } else if(strcmp(str, "push_front") == 0) {
            scanf("%d", &x);
            pushFront(&deque, x);
        } else if(strcmp(str, "pop_front") == 0) {
            printf("%d\n", popFront(&deque));
        } else if(strcmp(str, "pop_back") == 0) {
            printf("%d\n", popBack(&deque));
        } else if(strcmp(str, "size") == 0) {
            printf("%d\n", size(&deque));
        } else if(strcmp(str, "empty") == 0) {
            printf("%d\n", empty(&deque));
        } else if(strcmp(str, "front") == 0) {
            printf("%d\n", front(&deque));
        } else {
            printf("%d\n", back(&deque));
        }
    }

    return 0;
}