#include <stdio.h>
#include <string.h>

typedef struct Queue {
    int front = 0;
    int back = 0;
    int arr[10001];
} Queue;

void push(Queue* q, int x) {
    q -> arr[(q -> back)++] = x;
}

int pop(Queue* q) {
    if(q -> front == q -> back)
        return -1;
    return q -> arr[(q -> front)++];
}

int size(Queue* q) {
    return q -> back - q -> front;
}

int empty(Queue* q) {
    if(q -> front == q -> back) 
        return 1;
    else
        return 0;
}

int front(Queue* q) {
    if(q -> front == q -> back)
        return -1;
    return q -> arr[q -> front];
}

int back(Queue* q) {
    if(q -> front == q -> back)
        return -1;
    return q -> arr[(q -> back) - 1];
}


int main() {
    int N, x;
    char cmd[6];
    Queue q;

    scanf("%d\n", &N);
    for(int i = 0 ; i < N ; i++) {
        scanf("%s", cmd);
        if(strcmp(cmd, "push") == 0) {
            scanf("%d", &x);
            push(&q, x);
        } else if(strcmp(cmd, "pop") == 0) {
            printf("%d\n", pop(&q));
        } else if(strcmp(cmd, "size") == 0) {
            printf("%d\n", size(&q));
        } else if(strcmp(cmd, "empty") == 0) {
            printf("%d\n", empty(&q));
        } else if(strcmp(cmd, "front") == 0) {
            printf("%d\n", front(&q));
        } else {       
            printf("%d\n", back(&q));
        }
    }
    

    return 0;
}