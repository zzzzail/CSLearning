cmake_minimum_required(VERSION 3.20)
project(datastruct C)

set(CMAKE_C_STANDARD 99)

add_executable(
        datastruct
        src/SeqList.c
        src/LinkedList.c
        src/DLinkedList.c
        src/SeqStack.c
        src/SeqStackTest.c
        src/LinkedStack.c
        src/LinkedStackTest.c
        src/RecursionTest.c
        src/SeqQueue.c
        src/CirQueue.c
        src/LinkedQueue.c
        src/Matrix.c
        src/SymmetricalMatrix.c
        src/TSMatrix.c
        src/RMSMatrix.c
        src/GList.c
        src/BinTree.c
        src/BinThrTree.c
        src/PTree.c
        src/CTree.c
        src/HuffmanTree.c
        src/HuffmanCode.c
        src/MGraph.c
        src/ALGraph.c
        main.c)
