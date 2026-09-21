package JdbcPackage;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.HashSet;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		StudentDAO dao = new StudentDAO();
		Scanner sc = new Scanner(System.in);
		HashMap<Integer, String> studentMap = new HashMap<>();
		HashSet<String> courseSet = new HashSet<>();
		dao.loadStudentIntoMap(studentMap);
		dao.loadCoursesIntoSet(courseSet);

		while (true) {
			System.out.println("\n===Student Management System");
			System.out.println("1. ADD STUDENT");
			System.out.println("2. VIEW ALL STUDENTS");
			System.out.println("3. UPDATE STUDENT COURSE");
			System.out.println("4. DELETE STUDENT");
			System.out.println("5. VIEW STUDENT RESULT");
			System.out.println("6.  EXIT");
			System.out.println("Please SELECT FROM(1-6)");

			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				System.out.println("Inset Name: ");
				String name = sc.nextLine();
				System.out.println("Insert Email: ");
				String email = sc.nextLine();
				System.out.println("Insert course: ");
				String course = sc.nextLine();
				System.out.println("Insert Marks:");
				int marks = sc.nextInt();
				sc.nextLine();
				dao.addStudent(name, email, course, marks);
				dao.loadStudentIntoMap(studentMap);
				dao.loadCoursesIntoSet(courseSet);
				System.out.println("Student added to HashMap");
				System.out.println("Student added to HashSet");
				break;
			case 2:
				dao.viewStudent();
				System.out.println("\n HashMap students");
				for (Integer id : studentMap.keySet()) {
					System.out.println("ID:" + id + "| NAME:" + studentMap.get(id));
				}
				System.out.println("\n Unique Courses");
				for (String courseName : courseSet) {
					System.out.println(courseName);
				}

				System.out.println("\n Students Sorted By Name");
				ArrayList<String> names = new ArrayList<>(studentMap.values());
				names.sort((a, b) -> a.compareTo(b));
				for (String studentName : names) {
					System.out.println(studentName);
				}
				System.out.println("\n Students using Stream API");
				names.stream().forEach(studentName -> System.out.println(studentName));

				System.out.println("\n Name Starting With A");
				names.stream().filter(studentName -> studentName.startsWith("a"))
						.forEach(studentName -> System.out.println(studentName));

				System.out.println("\n Names In UpperCase");
				names.stream().map(studentName -> studentName.toUpperCase())
						.forEach(studentName -> System.out.println(studentName));

				System.out.println("\n Total Students");
				long totalStudents = names.stream().count();
				System.out.println("Total Students :" + totalStudents);

				System.out.println("Students Sorted Using Stream API");
				names.stream().sorted().forEach(studentName -> System.out.println(studentName));
				System.out.println("Names Starting ith A using Collect");
				ArrayList<String> aNames = names.stream().filter(studentName -> studentName.startsWith("a"))
						.collect(Collectors.toCollection(ArrayList::new));

				break;
			case 3:
				System.out.println("Insert ID of student whose course want to change");
				int studentId = sc.nextInt();
				sc.nextLine();
				if (studentMap.containsKey(studentId)) {
					System.out.println("Student found : " + studentMap.get(studentId));
					System.out.println("Insert new course: ");
					String newCourse = sc.nextLine();
					dao.updateStudentCourse(studentId, newCourse);
					dao.loadCoursesIntoSet(courseSet);
				} else {
					System.out.println("Student not found");
				}
				break;
			case 4:
				System.out.println("Insert the student Id which you want to delete: ");
				int deleteId = sc.nextInt();
				dao.deleteStudent(deleteId);
				studentMap.remove(deleteId);
				dao.loadCoursesIntoSet(courseSet);
				System.out.println("Student removed from HashMap");
				break;
			case 5:
				System.out.println("\n Insert Student ID:");
				int resultId = sc.nextInt();
				sc.close();
				dao.showResult(resultId);
			case 6:
				System.out.println("Software is shutting down");
				sc.close();
				System.exit(0);
				break;
			default:
				System.out.println("Invalid choice, Please select from (1-5)");
			}

		}
	}

}
