package model.service;

import model.config.SQLConfig;
import model.result.SQLResult;

public class ManagementService {
    SQLConfig config = new SQLConfig("MySQL", "localhost", "3306", "root", "");
    MySQLService service = new MySQLService(config);

    public ManagementService() {
        if(service.getService()) {
            String createDatabase = "CREATE DATABASE IF NOT EXISTS stu_m;";
            service.getResult(createDatabase);
            String useDatabase = "USE stu_m;";
            service.getResult(useDatabase);
            String createStudents = "CREATE TABLE IF NOT EXISTS students (student_id INT AUTO_INCREMENT PRIMARY KEY, student_name VARCHAR(50) NOT NULL);";
            service.getResult(createStudents);
            String createCourses = "CREATE TABLE IF NOT EXISTS courses (course_id INT AUTO_INCREMENT PRIMARY KEY, course_name VARCHAR(50) NOT NULL, " +
                                    "course_weekday VARCHAR(3) NOT NULL, course_start_time VARCHAR(5) NOT NULL, course_end_time VARCHAR(5) NOT NULL);";
            service.getResult(createCourses);
            String createStuCourses = "CREATE TABLE IF NOT EXISTS students_courses (student_id INT NOT NULL, course_id INT NOT NULL, PRIMARY KEY (student_id, course_id), CONSTRAINT students_courses_fk_students FOREIGN KEY (student_id) REFERENCES students (student_id),  CONSTRAINT students_courses_fk_courses FOREIGN KEY (course_id) REFERENCES courses (course_id));";
            service.getResult(createStuCourses);
        } else {
            System.err.println("Unable to connect to database");
        }
    }

    public SQLResult selectStu() {
        String query = "SELECT * FROM students";
        return service.getResult(query);
    }

    public SQLResult selectCourse() {
        String query = "SELECT * FROM courses";
        return service.getResult(query);
    }

    public SQLResult selectStuCourse() {
        String query = "SELECT * FROM students_courses";
        return service.getResult(query);
    }

    public SQLResult addStu(String stuName) {
        if (stuName.equals("")) {
            return null;
        }
        String addStuQuery = "INSERT INTO students VALUES (DEFAULT, \"" + stuName + "\")";
        return service.getResult(addStuQuery);
    }

    public SQLResult addCourse(String courseName, String courseWeekday, String startTime, String endTime) {
        if(courseName.equals("")){
            return null;
        }
        String addStuQuery = "INSERT INTO courses VALUES (DEFAULT, \"" + courseName +"\", \""+ courseWeekday + "\", " + "\"" + startTime + "\", \"" + endTime + "\")";
        return service.getResult(addStuQuery);
    }

    public SQLResult enrollInCourse(String student_id, String course_id) {
        if(course_id.equals("") || student_id.equals((""))) {
            return null;
        }
        String enrollCourse = "INSERT INTO students_courses VALUES (" + student_id +", "+ course_id + ")";
        return service.getResult(enrollCourse);
    }

    public SQLResult selectEnrolledStudentsByCourseId(String course_id) {
        String enrolledStudents = "SELECT s.student_id, s.student_name FROM students s JOIN students_courses sc ON s.student_id = sc.student_id WHERE sc.course_id = " + course_id;
        return service.getResult(enrolledStudents);
    }

    public SQLResult selectEnrolledCoursesByStudentId(String student_id) {
        String enrolledCourses = "SELECT c.course_id, c.course_name FROM courses c JOIN students_courses sc ON c.course_id = sc.course_id WHERE sc.student_id = " + student_id;
        return service.getResult(enrolledCourses);
    }

    public SQLResult selectCourseByStuNWeekday(String student_id, String weekday) {
        String query = "SELECT c.course_id, c.course_name, c.course_start_time, c.course_end_time FROM students s NATURAL JOIN students_courses sc NATURAL JOIN courses c " +
                        "WHERE s.student_id = " + student_id + " AND c.course_weekday = \"" + weekday + "\";";
        return service.getResult(query);
    }

    public void close() {
        service.close();
    }


}
