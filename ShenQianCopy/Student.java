package ShenQianCopy;

public class Student implements Cloneable {

    private String name;

    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public Object clone(){
        Student student = null;
        try {
            student = (Student) super.clone(); //浅拷贝

        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }

        student.address = (Address) address.clone();//深拷贝

        return student;
    }

}
