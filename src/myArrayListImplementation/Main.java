package myArrayListImplementation;

public class Main {
    public static void main(String[] args) {

        MyList<String> myCars = new MyList<>();

        myCars.add("Mazda");
        myCars.add("Chrysler");
        myCars.add("Chevrolet");
        myCars.add("Pegeout");
        myCars.add("Lada");
        myCars.add("Mitsubishi");
        myCars.add("Tesla");
        myCars.add("BMW");
        myCars.add("Nissan");
        myCars.add("Ferrari");
        myCars.add("Opel");
        myCars.add("Fiat");
        myCars.add("Volvo");
        myCars.add("Daewoo");

        System.out.printf("Размер автопарка %d машин." , myCars.size());
        System.out.println();
        myCars.printAll();
        System.out.println(myCars.indexOf("Volvo"));
        myCars.remove("BMW");
        myCars.remove(0);
        myCars.remove(8);

        System.out.printf("Размер автопарка %d машин." , myCars.size());
        System.out.println();
        myCars.printAll();
        System.out.println(myCars.hashCode());


    }
}