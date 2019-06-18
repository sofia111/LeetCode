package DesignedMode;
/**
*@Author: Sofia
*@Date: 2019/6/3 12:57
*@Description:  单例模式      //
*/

//懒汉写法 （线程不安全）
//public class Singleton {
//
//    private static Singleton singleton;
//
//    private Singleton(){};
//
//    public static Singleton getInstance(){
//        if (singleton == null)
//            singleton = new Singleton();
//        return singleton;
//    }
//}


//懒汉式写法（线程安全）
//public class Singleton{
//    private static Singleton instance;
//
//    private Singleton(){}
//    public static synchronized Singleton getInstance(){
//        if (instance == null)
//            instance = new Singleton();
//
//        return instance;
//    }
//}


//饿汉式写法
//public class Singleton{
//    private static Singleton instance = new Singleton();
//    private Singleton(){}
//    public static Singleton getInstance(){
//        return instance;
//    }
//}

//静态内部类
//public class Singleton{
//    private static class SingletonHolder{
//        private static final Singleton INSTANCE = new Singleton();
//    }
//
//    private Singleton(){}
//
//    public static final Singleton getInstance(){
//        return SingletonHolder.INSTANCE;
//    }
//}

// 枚举类
//public enum Singleton{
//    INSTANCE;
//    public void whateverMethod(){}
//}

    //双重校验锁
public class Singleton{
    private volatile static Singleton singleton;
    private Singleton(){}
    public static Singleton getSingleton(){
        if (singleton == null) {
            synchronized (Singleton.class){
                if (singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
