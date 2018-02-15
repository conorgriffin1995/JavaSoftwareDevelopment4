/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1skeleton;

/**
 *
 * @author pmage_000
 */
public class Manager extends Employee{
    
    private Employee [] staff;
    private int empCount = 0;
    
    private String deptName;
    
    public Manager(int id, String n, String ssn, double sal,String dName)
    {
        super(id,n,ssn,sal);
        staff = new Employee [20];
        deptName = dName;
    }
    
    
   
    
    public int findEmployee(Employee emp){
        int match = 0;
        for (int i = 0; i < staff.length; i++){
            if(emp.equals(staff[i])){
                match = i;
                break;
            }
            else{
                match = -1;
            }
        }
        return match;
    }
    
    public boolean addEmployee(Employee emp){
        if(findEmployee(emp) > 0)
        {
            return false;
        }else{
            staff[empCount] = emp;
            empCount ++;
            return true;
        }
    }
    
    public boolean removeEmployee(Employee emp){       
        boolean status = false;
        Employee[] temp = new Employee[staff.length];
        int count = 0;
        
        for(int i = 0; i < staff.length; i++){
            if(i == findEmployee(emp)){
                empCount--;
            }
            else{
                temp[count] = staff[i];
                count++;
                status = true;
            }
        }
        staff = temp;
        return status;
    }
    
    public String getDeptName()
    {
        return deptName;
    }
    
    public void printStaffDetails(){
        System.out.println("Staff of "+getName());
        for(int i = 0; i < empCount; i++){
            System.out.println("Name: "+staff[i].getName()+" Employee ID: "+staff[i].getID());
        }
    }
    
    public void print()
    {
        super.print();
        System.out.println("Department:      " + getDeptName());
    } 
  
}
