package JdbcPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;

public class StudentDAO {
	public void addStudent(String name, String email, String course, int marks) {
		String query = " INSERT INTO students(name,email,course,marks)VALUES(?,?,?,?)";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, course);
			pstmt.setInt(4, marks);
			int rows = pstmt.executeUpdate();
			if (rows > 0)
				System.out.println("Student added successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void viewStudent() {
		String query = "SELECT * FROM students";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery()) {
			System.out.println("\n===Students List===");
			while (rs.next()) {
				System.out.println(
						"ID:" + rs.getInt("id") + "| NAME:" + rs.getString("name") + "| EMAIL:" + rs.getString("email")
								+ "|COURSE:" + rs.getString("course") + "| MARKS:" + rs.getInt("marks"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getGrade(int marks) {
		if (marks >= 90) {
			return "A+";
		} else if (marks >= 80) {
			return "A";
		} else if (marks >= 70) {
			return "B";
		} else if (marks >= 60) {
			return "C";
		} else if (marks >= 50) {
			return "D";
		} else {
			return "F";
		}
	}

	public void showResult(int id) {
		String query = "SELECT name, marks FROM students Where id = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
				int marks = rs.getInt("marks");
				String grade = getGrade(marks);
				String result;
				if(marks>=50) {
					result = "PASS";
				}else {
					result = "FAIL";
				}
				System.out.println("Student Result");
				System.out.println("ID: " + id);
				System.out.println("NAME: " + name);
				System.out.println("MARKS: " + marks);
				System.out.println("GRADE: " + grade);
				System.out.println("RESULT :" + result);
			} else {
				System.out.println("Student Not Found!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateStudentCourse(int id, String newCourse) {

		String query = "UPDATE students SET course = ? WHERE id = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, newCourse);
			pstmt.setInt(2, id);
			int rows = pstmt.executeUpdate();
			if (rows > 0)
				System.out.println("Course updated");
			else
				System.out.println("NO STUDENT FOUND WITH THID ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteStudent(int id) {
		String query = "DELETE FROM students WHERE id =?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, id);
			int rows = pstmt.executeUpdate();
			if (rows > 0)
				System.out.println("Student deleted");
			else
				System.out.println("NO STUDENT FOUND WITH THIS ID");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadStudentIntoMap(HashMap<Integer, String> studentMap) {
		String query = "SELECT id,name FROM students";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery()) {
			studentMap.clear();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				studentMap.put(id, name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadCoursesIntoSet(HashSet<String> courseSet) {
		String query = "SELECT course FROM students";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery()) {
			courseSet.clear();
			while (rs.next()) {
				String course = rs.getString("course");
				courseSet.add(course);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
