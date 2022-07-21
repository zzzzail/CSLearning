/* TCP服务程序 */
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>

// 数据流大小
#define BUFFER_SIZE 1024
// 服务器端口号
#define SERVER_PORT 11121

void extract_name(char* buffer) {
    unsigned long len = strlen(buffer);
    int i = (int) len - 1;
    
    for ( ; i >= 0; i-- ) {
        if ( buffer[i] == ' ' ) {
            break;
        }
    }

    for ( int j = 0; j < len - i; j++ ) {
        buffer[j] = buffer[i + j + 1];
    }

    char temp[64];
    strcpy(temp, buffer);
    strcpy(buffer, "Hello ");
    strcat(buffer, temp);
    strcat(buffer, ", nice to meet you.");
}

/**
 * 主方法
 */
int main () {
    // 服务器端套接字描述符
    int server_sockfd;
    // 客户端套接字描述符
    int client_sockfd;
    // 接收套接字长度
    long recv_len;
    // 服务器地址
    struct sockaddr_in server_addr;
    // 客户端地址
    struct sockaddr_in client_addr;
    // 
    socklen_t sin_size = sizeof(struct sockaddr_in);
    // 
    char buffer[BUFFER_SIZE];
    // 
    memset(&server_addr, 0, sizeof(server_addr));
    server_addr.sin_family = AF_INET;
    server_addr.sin_addr.s_addr = INADDR_ANY;
    server_addr.sin_port = htons(SERVER_PORT);

    // 如果socket创建失败
    if ( (server_sockfd = socket(AF_INET, SOCK_STREAM, 0)) < 0 ) {
        printf("Failed to creat socket.\n");
        return -1;
    }

    // 如果绑定socket失败
    if ( bind(server_sockfd, (struct sockaddr*) &server_addr, sizeof(struct sockaddr_in)) < 0 ) {
        printf("Failed to bind socket.\n");
        return -1;
    }

    if ( listen(server_sockfd, 5) < 0 ) {
        printf("Failed to listen socket.\n");
    }

    // 循环监听来自客户端的socket
    while (1) {
        client_sockfd = accept(server_sockfd, (struct sockaddr*) &client_addr, &sin_size);

        while ( (recv_len = recv(client_sockfd, buffer, BUFFER_SIZE, 0)) > 0 ) {
            buffer[recv_len]= 0;
            printf("Recieve message from client: %s\n", buffer);
            extract_name(buffer);
            char temp[BUFFER_SIZE] = "";
            strcat(temp, buffer);
            printf("Send message: %s\n", temp);
            send(client_sockfd, temp, strlen(temp), 0);
        }
    }

    return 0;
}




