/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author jihun
 */
public class CourseQueries {
    private static Connection connection;
    private static PreparedStatement getAllCourses;
    private static PreparedStatement addCourses;
    private static PreparedStatement getAllCourseCodes;
    private static PreparedStatement getCourseSeats;
    private static PreparedStatement getCourseDescription;
    private static PreparedStatement displayCourseCode;
    private static PreparedStatement displayCourseDescription;
    private static PreparedStatement displayCourseSeats;
    private static PreparedStatement dropCourse;
    private static ResultSet resultSet;

    
    public static ArrayList<CourseEntry> getAllCourses(String semester) {
        connection = DBConnection.getConnection();
        ArrayList<CourseEntry> courses = new ArrayList<CourseEntry>();
        try{
            getAllCourses = connection.prepareStatement("select * from app.course where semester = ?");
            getAllCourses.setString(1, semester);
            resultSet = getAllCourses.executeQuery();
            while(resultSet.next()){
                courses.add((CourseEntry) resultSet.getObject(1));
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return courses;
    }
    
    public static void addCourses(CourseEntry course){
        connection = DBConnection.getConnection();
        try{
            addCourses = connection.prepareStatement("insert into app.course (semester, coursecode, description, seats) values (?, ?, ?, ?)");
            addCourses.setString(1, course.getSemester());
            addCourses.setString(2, course.getCourseCode());
            addCourses.setString(3, course.getCourseDescription());
            addCourses.setInt(4, course.getSeats());
            addCourses.executeUpdate();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<String> getAllCourseCodes(String semester){
        connection = DBConnection.getConnection();
        ArrayList<String> courseCodes = new ArrayList<String>();
        try{
            getAllCourseCodes = connection.prepareStatement("select coursecode from app.course where semester = ?");
            getAllCourseCodes.setString(1, semester);
            resultSet = getAllCourseCodes.executeQuery();
            while(resultSet.next()){
                courseCodes.add(resultSet.getString(1));
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return courseCodes;
    }
    
    public static int getCourseSeats(String semester, String courseCode){
        connection = DBConnection.getConnection();
        int seats = 0;
        try{
            getCourseSeats = connection.prepareStatement("select seats from app.course where semester = ? and coursecode = ?");
            getCourseSeats.setString(1, semester);
            getCourseSeats.setString(2, courseCode);
            resultSet = getCourseSeats.executeQuery();
            if (resultSet.next()){
                seats = resultSet.getInt(1);
            }
            
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return seats;
    }
    
    public static String getCourseDescription(String semester, String courseCode){
        connection = DBConnection.getConnection();
        String description = null;
        try{
            getCourseDescription = connection.prepareStatement("select description from app.course where semester = ? and coursecode = ?");
            getCourseDescription.setString(1, semester);
            getCourseDescription.setString(2, courseCode);
            resultSet = getCourseDescription.executeQuery();
            if (resultSet.next()){
                description = resultSet.getString(1);
            }
            
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return description;
    }
    
    public static void dropCourse(String smemester, String courseCode, String studentID){
        connection = DBConnection.getConnection();
        try{
            dropCourse = connection.prepareStatement("delete from app.schedule where semester = ? and coursecode = ? and studentid = ?");
            dropCourse.setString(1, smemester);
            dropCourse.setString(2, courseCode);
            dropCourse.setString(3, studentID);
            dropCourse.executeUpdate();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
}
