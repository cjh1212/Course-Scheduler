import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jihun
 */
public class StudentQueries {
    
    private static Connection connection;
    private static PreparedStatement addStudent;
    private static PreparedStatement getAllStudents;
    private static PreparedStatement getStudentsFirstName;
    private static PreparedStatement getStudentsLastName;
    private static PreparedStatement getStudentsID;
    private static PreparedStatement getID;
    private static PreparedStatement getStudent;
    private static PreparedStatement dropStudent;
    private static PreparedStatement getFirstName;
    private static PreparedStatement getLastName;
    private static ResultSet resultSet;
    
    public static void addStudent(StudentEntry student){
        connection = DBConnection.getConnection();
        try{
            addStudent = connection.prepareStatement("insert into app.student (studentid, firstname, lastname) values (?, ?, ?)");
            addStudent.setString(1, student.getStudentID());
            addStudent.setString(2, student.getFirstName());
            addStudent.setString(3, student.getLastName());
            addStudent.executeUpdate();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<StudentEntry> getAllStudents() {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> students = new ArrayList<>();
        try{
            getAllStudents = connection.prepareStatement("select * from app.student");
            resultSet = getAllStudents.executeQuery();
            while(resultSet.next()){
                students.add((StudentEntry) resultSet.getObject(1));
            }
            }catch(SQLException sqlException){
                sqlException.printStackTrace();
            }
        return students;
    }
    
    public static ArrayList<String> getStudentsFirstName(){
        connection = DBConnection.getConnection();
        ArrayList<String> firstName = new ArrayList<>();
        try{
            getStudentsFirstName = connection.prepareStatement("select firstname from app.student");
            resultSet = getStudentsFirstName.executeQuery();
            while(resultSet.next()){
                firstName.add(resultSet.getString(1));
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return firstName;
    }
    
        public static ArrayList<String> getStudentsLastName(){
        connection = DBConnection.getConnection();
        ArrayList<String> lastName = new ArrayList<>();
        try{
            getStudentsLastName = connection.prepareStatement("select lastname from app.student");
            resultSet = getStudentsLastName.executeQuery();
            while(resultSet.next()){
                lastName.add(resultSet.getString(1));
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return lastName;
    }
        
    public static ArrayList<String> getStudentsID(){
        connection = DBConnection.getConnection();
        ArrayList<String> id = new ArrayList<>();
        try{
            getStudentsID = connection.prepareStatement("select lastname from app.student");
            resultSet = getStudentsID.executeQuery();
            while(resultSet.next()){
                id.add(resultSet.getString(1));
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return id;
    }
    
    public static String getID(String firstName, String lastName){
        connection = DBConnection.getConnection();
        String studentID = null;
        try{
            getID = connection.prepareStatement("select studentid from app.student where firstname = ? and lastname = ?");
            getID.setString(1, firstName);
            getID.setString(2, lastName);
            resultSet = getID.executeQuery();
            if(resultSet.next()){
                studentID = resultSet.getString(1);
            }
        }catch(SQLException sqlException){
                sqlException.printStackTrace();
        }
        return studentID;
    }
    
    public static StudentEntry getStudent(String studentID){
        connection = DBConnection.getConnection();
        StudentEntry student = null;
        try{
            getStudent = connection.prepareStatement("select * from app.student where studentid = ?");
            getStudent.setString(1, studentID);
            resultSet = getStudent.executeQuery();
            while(resultSet.next()){
                student = (StudentEntry) resultSet.getObject(1);
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return student;
    }
    
    public static void dropStudent(String studentID){
        connection = DBConnection.getConnection();
        try{
            dropStudent = connection.prepareStatement("delete from app.student where studentid = ?");
            dropStudent.setString(1, studentID);
            dropStudent.executeUpdate();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static String getFirstName(String studentID){
        connection = DBConnection.getConnection();
        String firstName = null;
        try{
            getFirstName = connection.prepareStatement("select firstname from app.student where studentid = ?");
            getFirstName.setString(1, studentID);
            resultSet = getFirstName.executeQuery();
            while(resultSet.next()){
                firstName = resultSet.getString(1);
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return firstName;
    }
    
    public static String getLastName(String studentID){
        connection = DBConnection.getConnection();
        String lastName = null;
        try{
            getLastName = connection.prepareStatement("select lastname from app.student where studentid = ?");
            getLastName.setString(1, studentID);
            resultSet = getLastName.executeQuery();
            while(resultSet.next()){
                lastName = resultSet.getString(1);
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return lastName;
    }
}
