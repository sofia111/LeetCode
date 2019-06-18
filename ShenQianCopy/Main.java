package ShenQianCopy;

public class Main {

    public static void main(String[] args) {
        Student student1 = new Student();
        student1.setName("hhh");
        Address address = new Address();
        address.setAddress("hi");
        student1.setAddress(address);
        Student student3= student1;
        Student student2 = (Student) student1.clone();
        student2.setName("yyy");
        student3.setName("24356789");
        System.out.println(student1.getName()+"::"+student1.getAddress().getAddress() );
        System.out.println(student1.hashCode()== student2.hashCode());
        System.out.println(student2.getName()+"::"+student2.getAddress().getAddress());
        System.out.println(student3.getName()+"::"+student3.getAddress().getAddress());
        address.setAddress("h2");
        student2.setName("hhhh2");
        System.out.println(student1.getName()+"::"+student1.getAddress().getAddress());
        System.out.println(student2.getName()+"::"+student2.getAddress().getAddress());

    }

}
