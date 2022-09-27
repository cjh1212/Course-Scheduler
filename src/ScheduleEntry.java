import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author jihun
 */
public class ScheduleEntry {
    private String Semester;
    private String CourseCode;
    private String StudentID;
    private String Status;
    private Timestamp timestamp;

    public ScheduleEntry(String Semester, String CourseCode, String StudentID, String Status, Timestamp timestamp) {
        this.Semester = Semester;
        this.CourseCode = CourseCode;
        this.StudentID = StudentID;
        this.Status = Status;
        this.timestamp = timestamp;
    }
    
    
    
    public String getSemester() {
        return Semester;
    }

    public String getCourseCode() {
        return CourseCode;
    }

    public String getStudentID() {
        return StudentID;
    }

    public String getStatus() {
        return Status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
    
    
}
