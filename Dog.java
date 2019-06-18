public class Dog implements Cloneable{

    private String name;
    public Dog(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Dog clone() throws CloneNotSupportedException{
        return (Dog)super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {


        Dog dog1 = new Dog("A");
        Dog dog2 = dog1.clone();
        dog2.setName("B");
        System.out.println(dog1.name);
        System.out.println(dog2.name);
        Dog[] dogs1 = new Dog[]{dog1,dog2};
        Dog[] dogs2 = dogs1.clone();
          dogs2[0].setName("C");
        System.out.println(dogs1[0].name);
        System.out.println(dogs1[0].name);

    }
}
