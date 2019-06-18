package NIO.ChatRoom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
*@Author: Sofia
*@Email: feng-sofia@foxmail.com
*@Date: 2019/5/15 14:53
*@Description: NIO客户端
*/
public class NioClient {

    /*
    * 启动
    * */
    private  void start()throws IOException {

        /*
        * 连接服务器端
        * */
        SocketChannel socketChannel =  SocketChannel.open(
                new InetSocketAddress("127.0.0.1",8000)
        );
        /*
        * 向服务器端发送数据
        * */
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String request = scanner.nextLine();
            if (request != null && request.length() > 0){
                socketChannel.write(Charset.forName("UTF-8").encode(request));
            }
        }
        /*
        * 接收服务器端响应
        * */
        //新开线程，接收服务器端的响应数据
        //创建selecotr，socketchannel ，注册
        Selector selector = Selector.open();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        //客户端通过线程来处理服务器端响应
        new Thread(new NioClientHandler(selector)).start();

    }

    public static void main(String[] args)throws IOException {
        new NioClient().start();
    }
}
