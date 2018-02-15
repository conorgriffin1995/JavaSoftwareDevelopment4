package lab1skeleton;

import java.text.NumberFormat;

/**
 *
 * @author pmage_000
 */
public class Employee {
    private int empID;
    private String name;
    private String rsi;
    private double salary;
    
    
    public Employee(int id, String n, String rsi, double sal)
    {
        empID=id;
        name=n;
        this.rsi=rsi;
        salary=sal;
    }
    public void setID(int id)
    {
        empID=id;
    }
    public void setName(String n)
    {
        name=n;
    }
    public void setRSI(String rsi)
    {
        rsi = rsi;
    }
    public void setSalary(double s)
    {
        salary=s;
    }
    public int getID()
    {
        return empID;
    }
    public String getName()
    {
        return name;
    }
    public String getRSI()
    {
        return rsi;
    }
    public double getSalary()
    {
        return salary;
    }
    public void raiseSalary(double amt)
    {
        salary += amt;
    }
    public void print()
    {
       System.out.print("Employee ID:     " + getID() + "\n" +
               "Employee Name:   " + getName() + "\n" +
               "Employee RSI:    " + getRSI() + "\n" +
               "Employee Salary: " + NumberFormat.getCurrencyInstance().format(getSalary())+"\n");
    }
    
    
}
