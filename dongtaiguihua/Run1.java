package dongtaiguihua;
/**
*@Author: Sofia
*@Email: feng-sofia@foxmail.com
*@Date: 2019/5/10 20:45
*@Description: 实现Runnable接口，创建线程的类
*/
    public class Run1 implements java.lang.Runnable {
        private volatile int food=10;
        public void run() {
            for(int i=0;i<3;i++) {
                synchronized (this){
                    try {
                        Thread.sleep(1000);//出现线程不安全
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println("线程名："+Thread.currentThread().getName()+" food:"+food--);
                }

            }
        }
}
