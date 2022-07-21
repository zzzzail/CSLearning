/* TCP客户端 */
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

int main () {
    // 客户端套接字描述符
    int client_sockfd;
    long recv_len;
    struct sockaddr_in server_addr;
    char buffer[BUFFER_SIZE];

    // 设置内存
    memset(&server_addr, 0, sizeof(server_addr));
    memset(&buffer, 0, sizeof(buffer));

    server_addr.sin_family = AF_INET;
    server_addr.sin_addr.s_addr = inet_addr("127.0.0.1");
    server_addr.sin_port = htons(SERVER_PORT);

    if ( (client_sockfd = socket(AF_INET, SOCK_STREAM, 0)) < 0 ) {
        printf("Failed to creat socket.\n");
        return -1;
    }

    if ( connect(client_sockfd, (struct sockaddr*) &server_addr, sizeof(struct sockaddr)) < 0 ) {
        printf("Failed to connect remote socket.\n");
        return -1;
    }

    strcpy(buffer, "Hello Alice, I'm Bob");
    send(client_sockfd, buffer, strlen(buffer), 0);

    recv_len = recv(client_sockfd, buffer, BUFFER_SIZE, 0);
    buffer[recv_len] = 0;
    printf("Receieve message from server: %s\n", buffer);
    
    // 关闭tcp链接
    shutdown(client_sockfd, 0);
    
    return 0;
}
