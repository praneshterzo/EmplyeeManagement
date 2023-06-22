
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            // Register the MariaDB JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");
            // Create a connection
            String url = "jdbc:mariadb://localhost:3306/EmpDetails";
            String username = "root";
            String password = "root";
            connection = DriverManager.getConnection(url, username, password);
            // Create a statement
            statement = connection.createStatement();
            // Execute the query
            String query = "SELECT * FROM Employee";
            resultSet = statement.executeQuery(query);
            List<Employees> emp=new ArrayList<>();
            while (resultSet.next()) {
                String empName = resultSet.getString("name");
                String empEmail = resultSet.getString("email");
                String empPhone = resultSet.getString("phoneno");
                String empSalary = resultSet.getString("salary");
                String empDept = resultSet.getString("department");
                Date Joindate = resultSet.getDate("Joining_date");
                LocalDate empJoindate=Joindate.toLocalDate();
                Employees emplo=new  Employees(empName,empEmail,empPhone,empSalary,empDept,empJoindate);
                emp.add(emplo);
            }
            System.out.println("Details of all the Employee");
            emp.stream()
                            .forEach(System.out::println);

            System.out.println("Employees Details whose name starts with G : ");
            emp.stream()
            .filter(i->i.getName().startsWith("G"))
            .forEach(System.out::println);

            System.out.println("\n\nEmployees who did not update their mobile number : ");
            emp.stream()
                    .filter(i->i.getPhone()==null)
                    .forEach(System.out::println);

            System.out.println("\n\nEmployees who belong to QA with salary > 10000 : ");
            emp.stream()
                    .filter(i->Integer.valueOf(i.getSalary())>10000 && i.getDepartment().equals("QA"))
                    .forEach(System.out::println);

            System.out.println("\n\nEmployees who belong to IT : ");
            emp.stream()
                    .filter(i->i.getDepartment().equals("IT"))
                    .forEach(System.out::println);

            System.out.println("\n\nGiving 10% increment to employees who is DEV : ");
            System.out.println("Before Incrementing : ");

            emp.stream()
                    .filter(i->i.getDepartment().equals("DEV"))
                    .forEach(i-> System.out.println(i));

            System.out.println("After Incrementing : ");
            emp.stream()
                    .filter(i->i.getDepartment().equals("DEV")).map(i->new Employees(i.getName(),i.getEmail(),i.getPhone(),((Integer.valueOf(i.getSalary())+(0.1*Integer.valueOf(i.getSalary())))+""),i.getDepartment(),i.getJoiningDate()))
                    .forEach(System.out::println);

            System.out.println("\n\nEmployees joined between 01-Feb-2021 and 01-Apr-2021");
            LocalDate startDate = LocalDate.of(2021, 2, 1);
            LocalDate endDate = LocalDate.of(2021, 4, 1);
            emp.stream()
                    .filter(e -> e.getJoiningDate().isAfter(startDate) && e.getJoiningDate().isBefore(endDate))
                    .forEach(System.out::println);

            System.out.println("\n\nLowest Salary of an employee : ");
            int min=Integer.MAX_VALUE;
            System.out.println(emp.stream().map(Employees::getSalary).map(i->Integer.valueOf(i)).min(Integer::compare));

            System.out.println("\n\n3 recently joined Employees : ");
            emp.sort(Comparator.comparing(Employees::getJoiningDate).reversed());
            emp.stream().limit(3).forEach(System.out::println);

            System.out.println("\n\nSum of all salary joined in Feb 2021");
            LocalDate febStart = LocalDate.of(2021,2,1);
            LocalDate febEnd = LocalDate.of(2021,2,28);
            List<String> sal=emp.stream()
                    .filter(i->i.getJoiningDate()
                            .isBefore(febEnd)&&i.getJoiningDate().isAfter(febStart))
                    .map(Employees::getSalary).collect(Collectors.toList());
            int res = 0;
            for(String s:sal){
                res+=Integer.valueOf(s);
            }
            System.out.println(res);

            LocalDate febMid = LocalDate.of(2021,2,14);
            System.out.println("\n\nAverage salary for employee joined on 14-Feb-2021");
            sal= emp.stream()
                    .filter(i->i.getJoiningDate().isEqual(febMid))
                    .map(Employees::getSalary)
                    .collect(Collectors.toList());
            res=0;
            for(String s:sal){
                res+=Integer.valueOf(s);
            }
            if(res!=0)
            System.out.println(res/sal.size());
            else
                System.out.println("0");

            System.out.println("Data map with Salary and employee count");
            HashMap<Integer,Integer> details=new HashMap<Integer,Integer>();
            for(var x:emp){
                int sala=Integer.valueOf(x.getSalary());
                if(details.containsKey(sala)){
                    details.put(sala,details.get(sala)+1);
                }
                else
                    details.put(sala,1);
            }
            System.out.println(details);
            System.out.println("\n\nData map with Department id and employees count");

            Map<String,Integer> map1 = new HashMap<>();
            List<Employees> dev = emp.stream().filter(i->i.getDepartment().equals("DEV")).collect(Collectors.toList());
            List<Employees> qa = emp.stream().filter(i->i.getDepartment().equals("QA")).collect(Collectors.toList());
            List<Employees> it = emp.stream().filter(i->i.getDepartment().equals("IT")).collect(Collectors.toList());
            List<Employees> man = emp.stream().filter(i->i.getDepartment().equals("MANAGER")).collect(Collectors.toList());

            map1.put("DEV",dev.size());
            map1.put("QA", qa.size());
            map1.put("IT",it.size());
            map1.put("MANAGER", man.size());
            System.out.println(map1);

            System.out.println("\n\na data map with employee records grouped by department");
            Map<String,List<Employees>> map2 = new HashMap<>();
            map2.put("DEV",dev);
            map2.put("QA", qa);
            map2.put("IT",it);
            map2.put("MANAGER", man);
            System.out.println(map2);

            System.out.println("\n\na data map with department and their salary");
            List<String> devSalary= new ArrayList<>();
            List<String> qaSalary= new ArrayList<>();
            List<String> itSalary= new ArrayList<>();
            List<String> managerSalary= new ArrayList<>();
            for(String s:map2.keySet()){
                List<Employees> empo = map2.get(s);
                for(Employees e:empo){
                    if(s.equals("DEV"))
                        devSalary.add(e.getSalary());
                    else if(s.equals("QA"))
                        qaSalary.add(e.getSalary());
                    else if(s.equals("IT"))
                        itSalary.add(e.getSalary());
                    else
                        managerSalary.add(e.getSalary());
                }
            }
            Map<String,List<String>> map3 = new HashMap<>();
            map3.put("DEV",devSalary);
            map3.put("QA",qaSalary);
            map3.put("IT",itSalary);
            map3.put("MANAGER",managerSalary);
            System.out.println(map3);

            System.out.println("\n\n Most highest paid by category");
            Map<String ,Employees> map4 = new HashMap<>();
            int maxDev=0,maxQa=0,maxIt=0,maxManager=0;
            Employees maxDevEmployee = null,maxQaEmployee = null,maxItEmployee = null,maxManagerEmployee = null;
            for(Employees e:map2.get("DEV")){

                if(e.getSalary()!=null&&Integer.valueOf(e.getSalary())>maxDev){
                    maxDev=Integer.valueOf(e.getSalary());
                    maxDevEmployee=e;
                }
            }
            for(Employees e:map2.get("QA")){
                if(e.getSalary()!=null&&Integer.valueOf(e.getSalary())>maxQa){
                    maxQa=Integer.valueOf(e.getSalary());
                    maxQaEmployee=e;
                }
            }
            for(Employees e:map2.get("IT")){
                if(e.getSalary()!=null&&Integer.valueOf(e.getSalary())>maxIt){
                    maxIt=Integer.valueOf(e.getSalary());
                    maxItEmployee=e;
                }
            }
            for(Employees e:map2.get("MANAGER")){
                if(e.getSalary()!=null&&Integer.valueOf(e.getSalary())>maxManager){
                    maxManager=Integer.valueOf(e.getSalary());
                    maxManagerEmployee=e;
                }
            }
            map4.put("DEV",maxDevEmployee);
            map4.put("QA",maxQaEmployee);
            map4.put("IT",maxItEmployee);
            map4.put("MANAGER",maxManagerEmployee);

            System.out.println(map4);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}