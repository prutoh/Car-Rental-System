import java.util.ArrayList;
import java.util.Scanner;

class Car {
    private final String make;
    private final String model;
    private final int year;
    private final double price;
    private boolean available;

    public Car(String make, String model, int year, double price) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.available = true;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Customer {
    private final String name;
    private final int age;
    private final String gender;
    private final String drivingLicense;
    private final String nationalID;
    private Car bookedCar;

    public Customer(String name, int age, String gender, String drivingLicense, String nationalID) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.drivingLicense = drivingLicense;
        this.nationalID = nationalID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public String getNationalID() {
        return nationalID;
    }

    public Car getBookedCar() {
        return bookedCar;
    }

    public void bookCar(Car car) {
        this.bookedCar = car;
        car.setAvailable(false);
    }
}

class RentalAgency {
    private final ArrayList<Car> cars;
    private final ArrayList<Customer> customers;

    public RentalAgency(ArrayList<Car> cars) {
        this.cars = cars;
        this.customers = new ArrayList<>();
    }

    public ArrayList<Car> getAvailableCars() {
        ArrayList<Car> availableCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.isAvailable()) {
                availableCars.add(car);
            }
        }
        return availableCars;
    }

    public void bookCar(Car car, Customer customer) {
        customer.bookCar(car);
        customers.add(customer);
    }

    public void displayBookedCars() {
        if (customers.isEmpty()) {
            System.out.println("\n>>> No Booked Car At The Moment >>>");
        } else {
            System.out.println("Booked Cars:");
            for (Customer customer : customers) {
                Car car = customer.getBookedCar();
                System.out.println("Customer Name: " + customer.getName() + ", Car: " + car.getMake() + " " + car.getModel());
            }
        }
    }
}

public class CarRentalSystem {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ArrayList<Car> cars = new ArrayList<>();
            cars.add(new Car("Mercedes-Benz", "GLS 450", 2022, 15000.0));
            cars.add(new Car("Dodge", "Ram 1500 Rebel X", 2021, 14000.0));
            cars.add(new Car("Volkswagen", "Golf GTI Mk8 Clubsport 45", 2022, 9000.0));
            cars.add(new Car("Audi", "TTRS", 2022, 13500.0));
            cars.add(new Car("Porche", "Cayenne", 2022, 13000.0));
            cars.add(new Car("Ford", "Mustang GT", 2022, 10000.0));
            cars.add(new Car("Toyota", "Land Cruiser V8", 2022, 15000.0));
            cars.add(new Car("Honda", "Step-Wagon", 2022, 14000.0));
            cars.add(new Car("Volkswagen", "Transporter T7 Multivan", 2022, 16000.0));
            RentalAgency rentalAgency = new RentalAgency(cars);
            
            int choice;
            do {
                System.out.println("\n======THANK YOU FOR CHOOSING MWANANCHI CAR RENTALS AGENCY======");
                System.out.println("1. View Available Cars");
                System.out.println("2. Book a Car");
                System.out.println("3. Display Booked Cars");
                System.out.println("4. Exit");
                System.out.print("Enter Your Choice:- ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1 : displayAvailableCars(rentalAgency);
                        break;
                    case 2 : bookCar(rentalAgency, scanner);
                        break;
                    case 3 : rentalAgency.displayBookedCars();
                        break;
                    case 4 : System.out.println("\n*****WE HOPE YOU ENJOYED THE DRIVE FOR MWANANCHI CAR RENTALS AGENCY. GOODBYE & WELCOME AGAIN*****");
                        break;
                    default : System.out.println("\n--Invalid choice !!! Please Re-Try--");
                }
            } while (choice != 4);
        }
    }

    private static void displayAvailableCars(RentalAgency rentalAgency) {
        ArrayList<Car> availableCars = rentalAgency.getAvailableCars();
        if (availableCars.isEmpty()) {
            System.out.println("---No cars available to rent out for NOW!---");
        } else {
            System.out.println("\n<<<>>> HERE ARE THE AVAILABLE CARS <<<>>>\n");
            for (Car car : availableCars) {
                System.out.println("Make: " + car.getMake() + ", Model: " + car.getModel() + ", Year: " + car.getYear() + ", Price: Per Day= KSH." + car.getPrice());
            }
        }
    }

    private static void bookCar(RentalAgency rentalAgency, Scanner scanner) {
        ArrayList<Car> availableCars = rentalAgency.getAvailableCars();
        if (availableCars.isEmpty()) {
            System.out.println("\n---We Are Sorry! No cars available to rent out for Now!---.");
        } else {
            System.out.println("\nChoose Your Selection by Entering the Car index below-->");
            for (int i = 0; i < availableCars.size(); i++) {
                System.out.println((i + 1) + ". " + availableCars.get(i).getMake() + " " + availableCars.get(i).getModel());
            }
            System.out.print("\n<--Enter Car Index:-->\n");
            int carIndex = scanner.nextInt();
            if (carIndex >= 1 && carIndex <= availableCars.size()) {
                Car selectedCar = availableCars.get(carIndex - 1);
                scanner.nextLine();
                System.out.print("Enter Your Full Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Your Age: ");
                int age = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter Your Gender: ");
                String gender = scanner.nextLine();
                System.out.print("Enter Your Driving License Number: ");
                String drivingLicense = scanner.nextLine();
                System.out.print("Enter Your National ID Number: ");
                String nationalID = scanner.nextLine();
                Customer customer = new Customer(name, age, gender, drivingLicense, nationalID);
                rentalAgency.bookCar(selectedCar, customer);
                System.out.println("\n>>>__Car Booked Successfully__<<<");
            } else {
                System.out.println("\n***Invalid Car Index!***");
            }
        }
    }
}