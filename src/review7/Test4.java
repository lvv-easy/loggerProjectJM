package review7;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Test4 {

    public static ArrayList<Car> createSomeCars(Supplier<Car> carSupplier, int numberOfCars) {
        ArrayList<Car> cars = new ArrayList<>();
        for (int i = 0; i < numberOfCars; i++) {
            cars.add(carSupplier.get());
        }
        return cars;
    }

    public static void changeCar(Car car, Consumer<Car> carConsumer) {
        carConsumer.accept(car);
    }

    public static void main(String[] args) {
        ArrayList<Car> myCars = createSomeCars(() ->
                new Car("Skoda Octavia", "yellow metalic", 1.8), 5);

        System.out.println(myCars);

        changeCar(myCars.get(0), car -> {
            car.color = "blue";
            car.engine = 2.0;
            System.out.println("Upgraded car: " + car);
        });

        System.out.println(myCars);

    }

}

class Car {
    String model;
    String color;
    double engine;

    public Car(String model, String color, double engine) {
        this.model = model;
        this.color = color;
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", engine=" + engine +
                '}';
    }
}
