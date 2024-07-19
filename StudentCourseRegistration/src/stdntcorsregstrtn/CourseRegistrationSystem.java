package stdntcorsregstrtn;

import java.util.*;

class Course {
 private String courseCode;
 private String title;
 private String description;
 private int capacity;
 private String schedule;
 private List<String> registeredStudents;

 public Course(String courseCode, String title, String description, int capacity, String schedule) {
     this.courseCode = courseCode;
     this.title = title;
     this.description = description;
     this.capacity = capacity;
     this.schedule = schedule;
     this.registeredStudents = new ArrayList<>();
 }

 public String getCourseCode() {
     return courseCode;
 }

 public String getTitle() {
     return title;
 }

 public String getDescription() {
     return description;
 }

 public int getCapacity() {
     return capacity;
 }

 public String getSchedule() {
     return schedule;
 }

 public List<String> getRegisteredStudents() {
     return registeredStudents;
 }

 public boolean isFull() {
     return registeredStudents.size() >= capacity;
 }

 public boolean registerStudent(String studentID) {
     if (!isFull() && !registeredStudents.contains(studentID)) {
         registeredStudents.add(studentID);
         return true;
     }
     return false;
 }

 public boolean removeStudent(String studentID) {
     return registeredStudents.remove(studentID);
 }
}

class Student {
 private String studentID;
 private String name;
 private List<String> registeredCourses;

 public Student(String studentID, String name) {
     this.studentID = studentID;
     this.name = name;
     this.registeredCourses = new ArrayList<>();
 }

 public String getStudentID() {
     return studentID;
 }

 public String getName() {
     return name;
 }

 public List<String> getRegisteredCourses() {
     return registeredCourses;
 }

 public void registerCourse(String courseCode) {
     if (!registeredCourses.contains(courseCode)) {
         registeredCourses.add(courseCode);
     }
 }

 public void dropCourse(String courseCode) {
     registeredCourses.remove(courseCode);
 }
}

//CourseRegistrationSystem class
public class CourseRegistrationSystem {
 private Map<String, Course> courses;
 private Map<String, Student> students;

 public CourseRegistrationSystem() {
     this.courses = new HashMap<>();
     this.students = new HashMap<>();
 }

 public void addCourse(Course course) {
     courses.put(course.getCourseCode(), course);
 }

 public void addStudent(Student student) {
     students.put(student.getStudentID(), student);
 }

 public void listCourses() {
     for (Course course : courses.values()) {
         System.out.println("Course Code: " + course.getCourseCode());
         System.out.println("Title: " + course.getTitle());
         System.out.println("Description: " + course.getDescription());
         System.out.println("Capacity: " + course.getCapacity());
         System.out.println("Schedule: " + course.getSchedule());
         System.out.println("Available Slots: " + (course.getCapacity() - course.getRegisteredStudents().size()));
         System.out.println();
     }
 }

 public void registerStudentForCourse(String studentID, String courseCode) {
     Student student = students.get(studentID);
     Course course = courses.get(courseCode);

     if (student != null && course != null && course.registerStudent(studentID)) {
         student.registerCourse(courseCode);
         System.out.println("Student " + studentID + " registered for course " + courseCode);
     } else {
         System.out.println("Failed to register student " + studentID + " for course " + courseCode);
     }
 }

 public void dropStudentFromCourse(String studentID, String courseCode) {
     Student student = students.get(studentID);
     Course course = courses.get(courseCode);

     if (student != null && course != null && course.removeStudent(studentID)) {
         student.dropCourse(courseCode);
         System.out.println("Student " + studentID + " dropped from course " + courseCode);
     } else {
         System.out.println("Failed to drop student " + studentID + " from course " + courseCode);
     }
 }

 public static void main(String[] args) {
     CourseRegistrationSystem system = new CourseRegistrationSystem();

     system.addCourse(new Course("CS101", "Introduction to Computer Science", "Basic concepts of computer science.", 30, "MWF 10:00-11:00"));
     system.addCourse(new Course("MATH101", "Calculus I", "Introduction to calculus.", 25, "TTh 09:00-10:30"));

     system.addStudent(new Student("S001", "Alice"));
     system.addStudent(new Student("S002", "Bob"));

     System.out.println("Available Courses:");
     system.listCourses();

     system.registerStudentForCourse("S001", "CS101");
     system.registerStudentForCourse("S002", "CS101");
     system.registerStudentForCourse("S002", "MATH101");

     System.out.println("Available Courses After Registration:");
     system.listCourses();

     system.dropStudentFromCourse("S002", "CS101");

     System.out.println("Available Courses After Dropping a Course:");
     system.listCourses();
 }
}
