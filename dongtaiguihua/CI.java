package dongtaiguihua;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.*;
public class CI {

    //将txt文件转为数组
    public  String txt2String(File file) throws IOException
    {
        StringBuilder result =new StringBuilder();
        try {
            BufferedReader br= new BufferedReader(new FileReader(file));
            String s=null;
            while((s=br.readLine())!=null){
                result.append(System.lineSeparator()+s);
            }
            br.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result.toString();
    }
    //对文本进行预处理
    public  char[] preTreatment(char[] sourcefile)
    {
        char []afterfile = new char[10000];
        int index=0;
        if(sourcefile.length!=0)
        {
            for(int i=0;i<sourcefile.length;i++)
            {
                //去掉//注释后的一整行
                if(sourcefile[i]=='/'&&sourcefile[i+1]=='/')
                {
                    while(sourcefile[i]!='\n'){
                        i++;
                    }
                }
                //去掉/**/型注释中间的字符，若只检测到/*，未检测到*/，则提示注释有误
                if(sourcefile[i]=='/'&&sourcefile[i+1]=='*')
                {
                    i=i+2;
                    while(sourcefile[i]!='*'||sourcefile[i+1]!='/')
                    {
                        i++;
                        if(i==(sourcefile.length-1))
                        {
                            System.out.println("注释有误，未找到注释尾");
                            return afterfile;
                        }
                    }
                    i=i+2;
                }
                if(sourcefile[i]!='\n'&&sourcefile[i]!='\r'&&sourcefile[i]!='\t')
                {
                    afterfile[index]=sourcefile[i];
                    index++;
                }
            }
            index++;
            afterfile[index]='\0';
        }
        return afterfile;
    }
    //判断是否为字母
    public boolean isLetter(char c)
    {
        if((c>='a'&&c<='z')||(c>='A'&&c<'Z'))
        {
            return true;
        }
        else
            return false;
    }
    //判断是否为保留字，并返回编号
    public int isReserve(String s,String []reserve)
    {
        int index=-1;
        for(int i=0;i<reserve.length;i++)
        {
            if(s.equals(reserve[i]))
            {
                index=i;
                break;
            }
        }
        return index;
    }
    //判断是否为运算符，并返回编号
    public int isOperator(String s,String []operator)
    {
        int index=-1;
        for(int i=0;i<operator.length;i++)
        {
            if(s.equals(operator[i]))
            {
                index=i+110;
                break;
            }
        }
        return index;
    }
    //判断是否为界符，并返回编号
    public int isDivide(String s,char []divide)
    {
        int index=-1;
        for(int i=0;i<divide.length;i++)
        {
            if(s.equals(divide[i]+""))
            {
                index=i+150;
                break;
            }
        }
        return index;
    }
    //判断是否为数字
    public  boolean isDigit(char c)
    {
        if(c>='0'&&c<='9')
        {
            return true;
        }
        else
            return false;
    }
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        //关键字
        String []reserve={"if","else","while","throw","this","int","String","char","double","float","this",
                "static","public","private","default","switch","catch","void","try","return"};//0~99
        //运算符
        String []operator={"+","-","*","/","++","--","==","!=",">","<",">=",
                "<=","&&","||","!","&","|","^","~","<<",">>",">>>","+=","="};//110~149
        //界符
        char []divide={'<','>','(',')','{','}','[',']','\'','"',',',';','?','/','\\',':','.'};//150~无穷
        CI la=new CI();
        //源代码的txt文件
        File file=new File("D://Source.txt");
        //将txt格式的源文件放入sourcefile的字符数组中
        String source=la.txt2String(file);
        char sourcefile[] = source.toCharArray();
        //将源代码进行预处理，去掉注释和换行符
        char afterfile[]=la.preTreatment(sourcefile);
        //index记录源代码的字符数组扫描到的数组下标
        int index=0;
        //temp用于存储临时的字符串
        String temp="";
        //当未扫描到终结符则一直往下扫描
        while(afterfile[index]!='\0'){
            //当开头为字母时，可能为关键字或是标识符
            if(la.isLetter(afterfile[index]))
            {
                temp+=afterfile[index];
                //当下一个字符不为字母或数字，则停止扫描，并将扫描结果存入temp
                while(la.isLetter(afterfile[index+1])||la.isDigit(afterfile[index+1]))
                {
                    index++;
                    temp+=afterfile[index];
                }
                //将temp与关键字数组匹配，匹配成功即为关键字，否则为标识符
                if(la.isReserve(temp, reserve)!=-1)
                    System.out.println("保留字:("+la.isReserve(temp, reserve)+","+temp+")");
                else
                    System.out.println("标识符:("+100+","+temp+")");
            }
            //当开头为数字时，可能为整数或小数
            else if(la.isDigit(afterfile[index]))
            {
                temp+=afterfile[index];
                while(la.isDigit(afterfile[index+1]))
                {
                    index++;
                    temp+=afterfile[index];
                }
                //若在数字后有小数点，继续判断
                if(afterfile[index+1]=='.')
                {
                    index++;
                    //小数点后无数字，检测出错
                    if(!la.isDigit(afterfile[index+1])){
                        System.out.println("此处有误，小数点后无数字");
                        temp="";
                        break;
                    }
                    //小数点后有数字，检测为小数
                    else
                    {
                        temp+=afterfile[index];
                        while(la.isDigit(afterfile[index+1]))
                        {
                            index++;
                            temp+=afterfile[index];
                        }
                    }
                    System.out.println("小数常数:("+102+","+temp+")");
                }
                //无小数点，检测为整数
                else
                {
                    System.out.println("整数常数:("+100+","+temp+")");
                }
            }
            //既不是数字也不是字母也不是空格，则为界符或运算符，跳过空格
            else if(afterfile[index]!=' ')
            {
                temp+=afterfile[index];
                /*由于界符只有一个字符长度，则temp放入一个字符后直接开始匹配界符数组，
                 * 匹配成功则continue循环，匹配失败则继续扫描
                 */
                if(la.isDivide(temp, divide)!=-1)
                {
                    System.out.println("界符:("+la.isDivide(temp, divide)+","+temp+")");
                    temp="";
                    index++;
                    continue;
                }
                //判断是否为运算符
                else
                {
                    //若下一个字符也是符号类型则加入temp
                    while((la.isDivide(afterfile[index+1]+"", divide)==-1)&&(la.isDigit(afterfile[index+1])==false)
                            &&(la.isLetter(afterfile[index+1])==false))
                    {
                        index++;
                        temp+=afterfile[index];
                    }
                    //与运算符数组匹配，匹配成功，则为运算符，失败，则可能出现了检测不了的字符。
                    if(la.isOperator(temp, operator)!=-1)
                        System.out.println("运算符:("+la.isOperator(temp, operator)+","+temp+")");
                    else
                        System.out.println("无法识别，可能为中文字符");
                }

            }
            temp="";
            index++;
        }
    }


}

