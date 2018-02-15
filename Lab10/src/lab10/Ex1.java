package lab10;

import java.util.*;

/**
 *
 * @author x00111602
 */
public class Ex1 {
    public static void main(String[] args) {
        
    
    List<String> colours = new ArrayList<>();

    colours.add("Red");
    colours.add("Green");
    colours.add("Yellow");
    colours.add("Blue");
    colours.add("Pink");
    
    System.out.println("The List contains "+colours.size());
    
    //delete loop
//    for(int i = 0; i < colours.size(); i++)
//    {
//        colours.remove(i);
//    }
    
    
    //print loop
    for(int i = 0; i < colours.size(); i++)
    {
        System.out.println("Colour: "+colours.get(i));
    }
    
    Iterator itr1 = colours.iterator();

//    for(String c : colours){
//        colours.remove(c);
//    }
    System.out.println("");
    
//    while(itr1.hasNext())
//    {
//        String c = (String) itr1.next();
//        if(c.length() > 0)
//        {
//            itr1.remove();
//        }
//        
//    }   
    
//    for(Iterator<String> itr2 = colours.iterator();itr2.hasNext();)
//    {
//        String c = itr2.next();
//        if(c.length()>0)
//        {
//            itr2.remove();
//        }
//    }
    
//    colours.clear();
    
    
    
    System.out.println("Colour: "+ colours);
} 
}
