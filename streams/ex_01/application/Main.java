package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Employee;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter full file path: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<Employee> employees = new ArrayList<>();
            String line = br.readLine();
            while (line != null) {
                String[] employee = line.split(",");
                employees.add(new Employee(employee[0], employee[1], Double.parseDouble(employee[2])));
                line = br.readLine();
            }
            System.out.print("Enter salary: ");
            Double salary = sc.nextDouble();

            employees.stream().filter(e -> e.getSalary() > salary).map(e -> e.getEmail()).sorted()
                    .forEach(System.out::println);

            System.out.println(employees.stream().filter(e -> e.getName().startsWith("M")).map(e -> e.getSalary())
                    .reduce(0.00, (a, b) -> a + b));
        } catch (IOException e) {
            System.out.print(e);
        }
        sc.close();
    }
}
