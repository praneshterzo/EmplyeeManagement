import java.time.LocalDate;

public class Employees {
    private String Name;
    private String Email;
    private String Phoneno;
    private String Salary;
    private String Department;
    private LocalDate JoinDate;
    @Override
    public String toString() {
        return "Employee{" +
                "name='" + Name + '\'' +
                ", email='" + Email + '\'' +
                ", joiningDate=" + JoinDate +
                ", phone='" + Phoneno + '\'' +
                ", salary='" + Salary + '\'' +
                ", department='" + Department + '\'' +
                '}' + "\n";
    }
    public Employees(String Name, String Email, String Phoneno, String Salary, String Department, LocalDate JoinDate) {
        this.Name = Name;
        this.Email = Email;
        this.Phoneno = Phoneno;
        this.Salary = Salary;
        this.Department = Department;
        this.JoinDate = JoinDate;
    }
    public void setName(String name) {
        this.Name = Name;
    }
    public void setEmail(String email) {
        this.Email = Email;
    }

    public void setPhone(String phoneno) {
        this.Phoneno = Phoneno;
    }

    public void setSalary(String salary) {
        this.Salary = Salary;
    }

    public void setDepartment(String department) {
        this.Department = Department;
    }

    public void setJoiningDate(LocalDate joinDate) {
        this.JoinDate = JoinDate;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhone() {
        return Phoneno;
    }

    public String getSalary() {
        return Salary;
    }

    public String getDepartment() {
        return Department;
    }

    public LocalDate getJoiningDate() {
        return JoinDate;

    }

}

