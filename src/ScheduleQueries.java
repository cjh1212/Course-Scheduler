import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;

/**
 *
 * @author jihun
 */
public class ScheduleQueries {
    private static Connection connection;
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement getScheduleByStudent;
    private static PreparedStatement getScheduledStudentCount;
    private static PreparedStatement getCourseCode;
    private static PreparedStatement getStatus;
    private static PreparedStatement getScheduledStudentsByCourse;
    private static PreparedStatement getWaitlistedStudentsByCourse;
    private static PreparedStatement updateScheduleEntry;
    private static PreparedStatement getScheduledStudentsByCourseString;
    private static PreparedStatement getWaitListedStudentsByCourseString;
    private static PreparedStatement dropStudent;
    private static PreparedStatement dropStudentCoursesSemester;
    private static PreparedStatement dropStudentCoursesCourses;
    private static PreparedStatement getNextStudent;
    private static PreparedStatement dropCourse;
    private static PreparedStatement updateStudent;
    private static PreparedStatement dropCourseStudentList;
    
    
    private static ResultSet resultSet;
    
    public static void addScheduleEntry(ScheduleEntry entry){
        connection = DBConnection.getConnection();
        try{
            addScheduleEntry = connection.prepareStatement("insert into app.schedule (semester, studentid, coursecode, status, timestamp) values (?, ?, ?, ?, ?)");
            addScheduleEntry.setString(1, entry.getSemester());
            addScheduleEntry.setString(2, entry.getStudentID());
            addScheduleEntry.setString(3, entry.getCourseCode());
            addScheduleEntry.setString(4, entry.getStatus());
            addScheduleEntry.setTimestamp(5, entry.getTimestamp());
            addScheduleEntry.executeUpdate();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> schedule = new ArrayList<ScheduleEntry>();
        try{
            getScheduleByStudent = connection.prepareStatement("select (coursecode, status) from app.schedule where semester = ? and studentid = ?");
            getScheduleByStudent.setString(1, semester);
            getScheduleByStudent.setString(2, studentID);
            resultSet = getScheduleByStudent.executeQuery();
            while(resultSet.next()){
                schedule.add((ScheduleEntry) resultSet.getObject(1));
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return schedule;
    }
    
    public static int getScheduledStudentCount(String semester, String courseCode){
        connection = DBConnection.getConnection();
        int count = 0;
        try{
            getScheduledStudentCount = connection.prepareStatement("select studentID from app.schedule where semester = ? and coursecode = ?");
            getScheduledStudentCount.setString(1, semester);
            getScheduledStudentCount.setString(2, courseCode);
            resultSet = getScheduledStudentCount.executeQuery();
            while (resultSet.next()){
                count ++;
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return count;
    }
    
    //getcoursecode, getstatus
    public static ArrayList<String> getCourseCode(String semester, String studentID){
        connection = DBConnection.getConnection();
        ArrayList<String> courseCode = new ArrayList<String>();
        try{
            getCourseCode = connection.prepareStatement("select coursecode from app.schedule where semester = ? and studentID = ?");
            getCourseCode.setString(1, semester);
            getCourseCode.setString(2, studentID);
            resultSet = getCourseCode.executeQuery();
            while(resultSet.next()){
                courseCode.add(resultSet.getString(1));
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return courseCode;
    }
    
    public static String getStatus(String semester, String studentID, String courseCode){
        connection = DBConnection.getConnection();
        String status = null;
        try{
            getStatus = connection.prepareStatement("select status from app.schedule where semester = ? and studentid = ? and coursecode = ?");
            getStatus.setString(1, semester);
            getStatus.setString(2, studentID);
            getStatus.setString(3, courseCode);
            resultSet = getStatus.executeQuery();
            if(resultSet.next()){
                status = resultSet.getString(1);
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return status;
    }
    
    public static ArrayList<ScheduleEntry> getScheduledStudentsByCourse(String semester, String courseCode){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> schedule = new ArrayList<>();
        try{
            getScheduledStudentsByCourse = connection.prepareStatement("select studentid from app.schedule where semester = ? and coursecode = ? and status = ?");
            getScheduledStudentsByCourse.setString(1, semester);
            getScheduledStudentsByCourse.setString(2, courseCode);
            getScheduledStudentsByCourse.setString(3, "e");
            resultSet = getScheduledStudentsByCourse.executeQuery();
            while(resultSet.next()){
                schedule.add((ScheduleEntry) resultSet.getObject(1));
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return schedule;
    }
    
    public static ArrayList<String> getScheduledStudentsByCourseString(String semester, String courseCode){
        connection = DBConnection.getConnection();
        ArrayList<String> students = new ArrayList<>();
        try{
            getScheduledStudentsByCourseString = connection.prepareStatement("Select studentid from app.schedule where semester = ? and coursecode = ? and status = ?");
            getScheduledStudentsByCourseString.setString(1, semester);
            getScheduledStudentsByCourseString.setString(2, courseCode);
            getScheduledStudentsByCourseString.setString(3, "e");
            resultSet = getScheduledStudentsByCourseString.executeQuery();
            while(resultSet.next()){
                students.add(resultSet.getString(1));
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return students;
    }
    
    public static ArrayList<ScheduleEntry> getWaitlistedStudentsByCourse(String semester, String courseCode){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> schedule = new ArrayList<>();
        try{
            getWaitlistedStudentsByCourse = connection.prepareStatement("select studentid from app.schedule where semester = ? and coursecode = ? and status = ?");
            getWaitlistedStudentsByCourse.setString(1, semester);
            getWaitlistedStudentsByCourse.setString(2, courseCode);
            getWaitlistedStudentsByCourse.setString(3, "w");
            resultSet = getWaitlistedStudentsByCourse.executeQuery();
            while(resultSet.next()){
                schedule.add((ScheduleEntry)resultSet.getObject(1));
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return schedule;
    }
    
    public static ArrayList<String> getWaitListedStudentsByCourseString(String semester, String courseCode){
        connection = DBConnection.getConnection();
        ArrayList<String> students = new ArrayList<>();
        try{
            getWaitListedStudentsByCourseString = connection.prepareStatement("select studentid from app.schedule where semester = ? and coursecode = ? and status = ?");
            getWaitListedStudentsByCourseString.setString(1, semester);
            getWaitListedStudentsByCourseString.setString(2, courseCode);
            getWaitListedStudentsByCourseString.setString(3, "w");
            resultSet = getWaitListedStudentsByCourseString.executeQuery();
            while(resultSet.next()){
                students.add(resultSet.getString(1));
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return students;
    }
    
    public static void dropStudent(String studentID){
        connection = DBConnection.getConnection();
        try{
            dropStudent = connection.prepareStatement("delete from app.schedule where studentid = ?");
            dropStudent.setString(1, studentID);
            dropStudent.executeUpdate();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<String> dropStudentCoursesSemester(String studentID){
        connection = DBConnection.getConnection();
        ArrayList<String> semester = new ArrayList<>();
        try{
            dropStudentCoursesSemester = connection.prepareStatement("Select semester from app.schedule where studentid = ?");
            dropStudentCoursesSemester.setString(1, studentID);
            resultSet = dropStudentCoursesSemester.executeQuery();
            while(resultSet.next()){
                semester.add(resultSet.getString(1));
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return semester;
    }
    
    public static ArrayList<String> dropStudentCoursesCourses(String studentID){
        connection = DBConnection.getConnection();
        ArrayList<String> courses = new ArrayList<>();
        try{
            dropStudentCoursesCourses = connection.prepareStatement("Select coursecode from app.schedule where studentid = ?");
            dropStudentCoursesCourses.setString(1, studentID);
            resultSet = dropStudentCoursesCourses.executeQuery();
            while(resultSet.next()){
                courses.add(resultSet.getString(1));
            }
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return courses;
    }
    
    public static String getNextStudent(String semester, String courseCode){
        connection = DBConnection.getConnection();
        String student = null;
        try{
            getNextStudent = connection.prepareStatement("select studentid from app.schedule where semester = ? and coursecode = ? and status = ? order by timestamp");
            getNextStudent.setString(1, semester);
            getNextStudent.setString(2, courseCode);
            getNextStudent.setString(3, "w");
            resultSet = getNextStudent.executeQuery();
            while(resultSet.next()){
                student = resultSet.getString(1);
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return student;
    }
    
    public static void dropCourse(String semester, String courseCode){
        connection = DBConnection.getConnection();
        try{
            dropCourse = connection.prepareStatement("delete from app.schedule where semester = ? and coursecode = ?");
            dropCourse.setString(1, semester);
            dropCourse.setString(2, courseCode);
            dropCourse.executeUpdate();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static void updateStudent(String semester, String courseCode, String studentID){
        connection = DBConnection.getConnection();
        try{
            updateStudent = connection.prepareStatement("update app.schedule set status = ? where semester = ? and coursecode = ? and studentid=?");
            updateStudent.setString(1, "e");
            updateStudent.setString(2, semester);
            updateStudent.setString(3, courseCode);
            updateStudent.setString(4, studentID);
            updateStudent.executeUpdate();
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<String> dropCourseStudentList(String semester, String courseCode){
        connection = DBConnection.getConnection();
        ArrayList<String> students = new ArrayList<>();
        try{
            dropCourseStudentList = connection.prepareStatement("select studentID from app.schedule where semester = ? and coursecode = ?");
            dropCourseStudentList.setString(1, semester);
            dropCourseStudentList.setString(2, courseCode);
            resultSet = dropCourseStudentList.executeQuery();
            while(resultSet.next()){
                students.add(resultSet.getString(1));
            }
        }catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return students;
    }
    
//    public static void orderByTimestamp(){
//        connection = DBConnection.getConnection();
//        try{
//            orderByTimestamp = connection.prepareStatement("select ")
//        }
//    }
    
//    public static void updateScheduleEntry(String semester, ScheduleEntry entry){
//        
//    }
    
}
