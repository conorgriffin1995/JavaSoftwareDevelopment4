package lab10;
import java.util.*;

//X00111602

public class Ex2 {
    
    public static void main(String[] args) {
        
        //Create a hash map
        HashSet<String> hm = new HashSet<>();
        
        //Put elements to the map
        hm.add("Red");
        hm.add("Green");
        hm.add("Yellow");
        hm.add("Blue");
        hm.add("Pink");
        
        System.out.println(hm);
        
        System.out.println("");
        System.out.println("=====SORTING=====");
        System.out.println("");
        
        List<String> myList = new ArrayList(hm);
        Collections.sort(myList);
        System.out.println(myList);
        
    }
    
}
