package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }
        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
        User foo = new User("Ivan", "Ivanov", "ivan-ivanov@mail.ru");
        Car bar = new Car();
        bar.setModel("BMW");
        bar.setSeries(7);
        foo.setCar(bar);
        userService.add(foo);
        CarService carService = context.getBean(CarService.class);
        List<Car> cars = carService.listCars();
        User carOwner = userService.getUserByCarModelAndSerial(bar.getModel(), bar.getSeries());
        System.out.println();
        System.out.println("Владелец машины: " + carOwner);
        System.out.println();
        for (Car car : cars) {
            carService.delete(car);
        }
        for (User user : users) {
            userService.delete(user);
        }
        context.close();
    }
}
