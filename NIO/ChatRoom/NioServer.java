package NIO.ChatRoom;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
*@Author: Sofia
*@Email: feng-sofia@foxmail.com
*@Date: 2019/5/15 14:53
*@Description: NIO服务端
*/
public class NioServer {

    /**
     * @Description:启动服务器方法
    * @params a
     * @return: a
     **/
    public void start() throws IOException {
        /*
        * 1. 创建Selector
        * */

        Selector selector = Selector.open();
        /*
        * 2. 通过ServerSocketChannel创建channel通过
        * */
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();


        /*
        * 3. 为channel通道绑定监听端口
        * */
        serverSocketChannel.bind(new InetSocketAddress(8000));

        /*
        *4. 设置channel为非阻塞模式
        * */
        serverSocketChannel.configureBlocking(false);

        /*
        * 5. 将channel注册到selector上，监听连接事件
        * */
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器成功启动");

        /*
        * 6. 循环等待新接入的连接
         * */
        for (;;){//while (thrue)
            /*
            *TODO 获取可用channel数量
            * */
            int readyChannels =  selector.select();//本身阻塞方法，只有channel就绪才会返回
            /*
            * TODO
            * */
            if (readyChannels == 0)continue;

            /*
            *获取可用channel集合
             **/
            Set<SelectionKey> selectionKeys =  selector.selectedKeys();

            Iterator iterator = selectionKeys.iterator();

            while (iterator.hasNext()){
                /*
                * selectionKey实例
                * */
                SelectionKey selectionKey = (SelectionKey) iterator.next();

                /*
                * 移除Set中的当前selectionKey
                * */
                iterator.remove();

                /*
                 * 7. 根据就绪状态，调用对应方法处理业务逻辑
                 * */

                /*
                * 如果是接入事件，
                * */
                if (selectionKey.isAcceptable()){
                    acceptHandler(serverSocketChannel,selector);
                }

                /*
                * 如果是可读事件
                * */
                if (selectionKey.isReadable()){
                    readHandler(selectionKey,selector);
                }
            }
        }


    }

    /**
     * @Description:接入事件处理器
     * @param: a
     * @retun: a
     **/
    private void acceptHandler(ServerSocketChannel serverSocketChannel,Selector selector)throws IOException{
        /*
        * 如果是接入事件，创建socketChannel
        * */
        SocketChannel socketChannel = serverSocketChannel.accept();
        /*
        * 将socketChannel设置为非阻塞工作模式
        * */
        socketChannel.configureBlocking(false);
        /*
        * 将channel注册到selector上，监听可读事件
        * */
        socketChannel.register(selector,SelectionKey.OP_READ);

        /*
        * 回复客户端提示信息
        * */
        socketChannel.write(Charset.forName("UTF-8").encode("你与其他人不是朋友关系，请注意隐私安全"));
    }

    public void readHandler(SelectionKey selectionKey,Selector selector)throws IOException {

        /**
         *@Description: 可读事件处理
         *@params: [selectionKey, selector]
         *@return: void
         **/

        /*
         * 从selectorKey中获取到就绪的channel
         * */
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();


        /*
         * 创建buffer
         * */
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        /*
         * 循环读取客户端信息
         * */
        String request = "";
        while (socketChannel.read(byteBuffer) > 0){

            /*
             * 切换buffer为读模式
             * */
            byteBuffer.flip();
            request += Charset.forName("UTF-8").decode(byteBuffer);
        }

        /*
         * 将channel再次注册到selector上，监听他的可读事件
         * */
        socketChannel.register(selector,SelectionKey.OP_READ);

        /*
         * 将客户端发送的请求消息广播给其他客户端
         * */
        if (request.length() > 0){
            //广播给其他用户
            System.out.println(";:"+ request);
        }
    }

    /*
    * 广播给其他用户
    * */
    private void broadCast(Selector selector,SocketChannel sourceChannel,String request){

        /*
        * 获取到所有以接入的客户端channel
        * */
        Set<SelectionKey> selectionKeySet = selector.keys();

        /*
        * 循环向所有channel广播信息
        * */
        selectionKeySet.forEach(selectionKey -> {
            Channel targetChannel = selectionKey.channel();
            //剔除发消息的客户端
            if (targetChannel instanceof SocketChannel && targetChannel != sourceChannel){
                try {
                    ((SocketChannel) targetChannel).write(
                       Charset.forName("UTF-8").encode(request));
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

        });
    }

    public static void main(String[] args) throws IOException {
        NioServer nioServer = new NioServer();
        nioServer.start();
    }

}
