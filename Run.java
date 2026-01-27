public class Run {
    public static void main(String[] args) {
        Saab95 car = new Saab95();
        car.startEngine();
        car.gas(0.1);
        System.out.println("Hello");
    }
}