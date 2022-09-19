package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import entities.Person;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter full file path: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<Person> people = new ArrayList<>();
            String line = br.readLine();
            while (line != null) {
                String[] person = line.split(",");
                people.add(new Person(person[0], Integer.parseInt(person[1])));
                line = br.readLine();
            }
            System.out.println("Average salary: ");

            int taller = people.stream().map(e -> e.getHeight()).max(Comparator.comparing(Integer::valueOf)).get();

            int shorter = people.stream().map(e -> e.getHeight()).min(Comparator.comparing(Integer::valueOf)).get();

            double avg = people.stream().filter(e -> e.getGender().equals("Male")).map(e -> e.getHeight()).reduce(0,
                    (a, b) -> a + b) / people.stream().filter(e -> e.getGender().equals("Male")).count();
            
            long female = people.stream().filter(e -> e.getGender().equals("Female")).count();

            System.out.println("Taller: " + taller + ", shorter: "+ shorter);
            System.out.println("Average male height: " + avg);
            System.out.println("Amount of female in list: " + female);
        } catch (IOException e) {
            System.out.print(e);
        }
        sc.close();
    }
}
