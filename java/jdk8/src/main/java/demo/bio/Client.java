package demo.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author zail
 * @date 2022/6/3
 */
public class Client {
    
    public static void main(String[] args) throws IOException {
        // 创建请求服务端的 socket
        Socket socket = new Socket("127.0.0.1", 9999);
        // 从 socket 对象中获取字符输出流
        OutputStream os = socket.getOutputStream();
        PrintStream ps = new PrintStream(os);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入：");
            String msg = scanner.nextLine();
            ps.println(msg);
            ps.flush();
        }
        // scanner.close();
        // ps.close();
        // os.close();
        // socket.close();
    }
    
}
