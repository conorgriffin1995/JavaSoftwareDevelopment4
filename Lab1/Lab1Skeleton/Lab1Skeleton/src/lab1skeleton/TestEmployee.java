package lab1skeleton;

public class TestEmployee {
    public static void main(String args[])
    {
        Engineer eng1 = new Engineer(101,"Jane Smith","2378675D",120345.27);
        Engineer eng2 = new Engineer(120,"Bill Lecomte","6754341N",110450.34);
        Admin a = new Admin(304,"Bill Munroe","0978654V",75002.34);
        Manager m = new Manager(207,"Barbara Johnson","8765667Y",109501.36,"European Marketing");
        Director d = new Director(122,"Susan Wheeler","9876547B",120567.36,"Global Marketing",1000000.0);
        
        
        
        eng1.print();
        System.out.println("-----------------");
        eng2.print();
        System.out.println("-----------------");
        a.print();
        System.out.println("-----------------");
        m.print();
        System.out.println("-----------------");
        d.print();
        System.out.println("-----------------");
        
        System.out.println("");
        System.out.println("Testing raiseSalary and setName on Manager");
        m.raiseSalary(10000);
        m.setName("Barbara Johnson-Smyth");
        System.out.println("-----------------");
        m.print();
        System.out.println("-----------------");
        System.out.println("");
        
        if(m.addEmployee(a)==true){
            System.out.println("Success: Admin added");
        }else{
            System.out.println("failed to add");
        }
        if(m.addEmployee(eng1)==true){
            System.out.println("Success: eng1 added");
        }else{
            System.out.println("failed to add");
        }
        if(m.addEmployee(eng2)==true){
            System.out.println("Success: eng2 added");
        }else{
            System.out.println("failed to add");
        }
        System.out.println("");
        m.printStaffDetails();
        System.out.println("");
        if(m.removeEmployee(eng1)== true){
        System.out.println("Success: removed eng1");
        
        }else{
            System.out.println("Failed to remove employee");
        }
        System.out.println("");
        m.printStaffDetails();
    }
    
}
