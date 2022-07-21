#include "CirQueue.h"

void InitQueue(CirQueue *Q) {
    Q->front = Q->rear = 0;
}

int QueueEmpty(CirQueue *Q) {
    return Q->rear == Q->front;
}

int QueueFull(CirQueue *Q) {
    return (Q->rear + 1) % QueueSize == Q->front;
}

