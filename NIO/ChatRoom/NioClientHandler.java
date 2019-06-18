package NIO.ChatRoom;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
*@Author: Sofia
*@Email: feng-sofia@foxmail.com
*@Date: 2019/5/15 18:23
*@Description: 客户端线程类，接收服务器端响应信息
*/
public class NioClientHandler implements Runnable{

    private Selector selector;

    public NioClientHandler(Selector selector){

        this.selector = selector;
    }

    @Override
    public void run() {

        try {
            /*

             * 6. 循环等待新接入的连接
             * */
            for (;;) {//while (thrue)
                /*
                 *TODO 获取可用channel数量
                 * */
                int readyChannels = selector.select();//本身阻塞方法，只有channel就绪才会返回
                /*
                 * TODO
                 * */
                if (readyChannels == 0) continue;

                /*
                 *获取可用channel集合
                 **/
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                Iterator iterator = selectionKeys.iterator();

                while (iterator.hasNext()) {
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
                     * 如果是可读事件
                     * */
                    if (selectionKey.isReadable()) {
                        readHandler(selectionKey, selector);
                    }
                }
            }
        }catch (IOException e){
               e.printStackTrace();
        }
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
         * 循环读取服务器端响应信息
         * */
        String response = "";
        while (socketChannel.read(byteBuffer) > 0){

            /*
             * 切换buffer为读模式
             * */
            byteBuffer.flip();
            response += Charset.forName("UTF-8").decode(byteBuffer);
        }

        /*
         * 将channel再次注册到selector上，监听他的可读事件
         * */
        socketChannel.register(selector,SelectionKey.OP_READ);

        /*
         * 将服务端响应信息打印到本地
         * */
        if (response.length() > 0){
            //广播给其他用户
            System.out.println( response);
        }
    }

}
