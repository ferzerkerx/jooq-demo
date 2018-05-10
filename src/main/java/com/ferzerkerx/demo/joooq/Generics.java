package com.ferzerkerx.demo.joooq;

import java.util.*;

public class Generics {

    public static List<Person> findAll() {
        return Arrays.asList(
                new Person("person1"),
                new Employee("person2"),
                new Person("person3"),
                new Employee("person4"),
                new Person("person5")
        );
    }

    private static List<Employee> findAllEmployee() {
        return Arrays.asList(
                new Employee("person2"),
                new Employee("person4")
        );
    }

    public static void main(String [] args) {
        List<Person> all = findAll();
        Person maxPerson = max(all);
        System.out.println(maxPerson);

        List<Employee> allEmployee = findAllEmployee();
        Employee maxEmployee = max(allEmployee);
        System.out.println(maxEmployee);

    }

    //    private Person max(List<? extends Person> persons) {
//
//    }
    private static <T extends Comparable<? super T>> T max(List<? extends T> persons) {
        Iterator<? extends T> iterator = persons.iterator();
        T max = iterator.next();
        while(iterator.hasNext()) {
            T next = iterator.next();
            int comparisonResult = max.compareTo(next);
            if (comparisonResult < 0) {
                max = next;
            }
        }
        return max;
    }

}

class Person<T extends Person<T>> implements Comparable<T> {
    private final String firstName;
    private String lastName;

    Person(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public int compareTo(Person o) {
        return o.firstName.compareTo(firstName);
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

class Employee extends Person<Employee> {
    private final UUID employeeId = UUID.randomUUID();

    Employee(String firstName) {
        super(firstName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(employeeId, employee.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), employeeId);
    }


    @Override
    public int compareTo(Employee o) {
        return employeeId.compareTo(o.employeeId);
    }

    @Override
    public String toString() {
        return "Employee{" +
                super.toString()+
                "employeeId=" + employeeId +
                '}';
    }
}
