package review7;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StudentInfo {
    public static void main(String[] args) {
        Student st1 = new Student("Ivan", 'm', 22, 3, 8.3);
        Student st2 = new Student("Nikolay", 'm', 28, 2, 6.4);
        Student st3 = new Student("Elena", 'f', 19, 1, 8.9);
        Student st4 = new Student("Petr", 'm', 35, 4, 7);
        Student st5 = new Student("Marya", 'f', 23, 3, 9.1);

        ArrayList<Student> students = new ArrayList<>();
        students.add(st1);
        students.add(st2);
        students.add(st3);
        students.add(st4);
        students.add(st5);



//        Student first = students.stream().map(el ->
//        {
//            el.setName(el.getName().toUpperCase());
//            return el;
//        }).filter(el -> el.getSex() == 'f')
//                .sorted((x, y) -> x.getAge() - y.getAge())
//                .findFirst().get();
//        System.out.println(first);

//        Map<Integer, List<Student>> courseMap = students.stream().map(el -> {
//            el.setName(el.getName().toUpperCase());
//            return el;})
//                .collect(Collectors.groupingBy(el -> el.getCourse()));
//
//        for (Map.Entry<Integer, List<Student>> entry : courseMap.entrySet()) {
//            System.out.println(entry.getKey() + " : " + entry.getValue().toString());
//        }

//        Map<Boolean, List<Student>> courseMap = students.stream()
//                .collect(Collectors.partitioningBy(el -> el.getAvgGrade() > 8));
//
//        for (Map.Entry<Boolean, List<Student>> entry : courseMap.entrySet()) {
//            System.out.println(entry.getKey() + " : " + entry.getValue().toString());
//        }

        StudentInfo studentInfo = new StudentInfo();

//        Faculty f1 = new Faculty("IT");
//        Faculty f2 = new Faculty("Philosophy");
//
//        f1.addStudentsOnFaculty(st1);
//        f1.addStudentsOnFaculty(st2);
//        f1.addStudentsOnFaculty(st3);
//        f2.addStudentsOnFaculty(st4);
//        f2.addStudentsOnFaculty(st5);
//
//        List<Faculty> faculties = new ArrayList<>();
//        faculties.add(f1);
//        faculties.add(f2);
//
//        faculties.stream().flatMap(faculty -> faculty.getStudentsOnFaculty().stream()).sorted((s1, s2) -> s1.name.compareTo(s2.name)).forEach(e -> System.out.println(e.name));


    }

    private static double avgOfSomething(List<Student> list, Function<Student, Double> f) {
        double result = 0;
        for (Student s : list) {
            result += f.apply(s);
        }

        return result / list.size();
    }

    void testStudents(ArrayList<Student> a1, Predicate<Student> p) {
        for (Student s : a1) {
            if (p.test(s)) {
                System.out.println(s);
            }
        }
    }
}

class Faculty {
    String name;
    List<Student> studentsOnFaculty;

    public Faculty(String name) {
        this.name = name;
        studentsOnFaculty = new ArrayList<>();
    }

    public List<Student> getStudentsOnFaculty() {
        return studentsOnFaculty;
    }

    public void addStudentsOnFaculty(Student student) {
        studentsOnFaculty.add(student);
    }
}

