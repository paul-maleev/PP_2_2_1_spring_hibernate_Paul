package hiber.dao;

import hiber.model.Car;

import java.util.List;

public interface CarDao {
    void add(Car car);

    void delete(Car car);

    List<Car> listCars();
}
