package lab10;

import java.util.*;

//X00111602
public class Ex3 {

    public static void main(String[] args) {
        String word;
        boolean continueLoop = true;

        Map<String, String> hm = new HashMap<>();

        hm.put("render", "to cause to be or become; make:");
        hm.put("immoderate", "exceeding just or reasonable limits");
        hm.put("foliaceous", "pertaining to or consisting of leaves");
        hm.put("insubordinate", "not submitting to authority; disobedient:");
        hm.put("creek", "a stream, brook, or a minor tributary of a river");

        Set set = hm.entrySet();

        Iterator i = set.iterator();
        do {

            while (i.hasNext()) {
                Map.Entry me = (Map.Entry) i.next();
                System.out.print(me.getKey() + ": ");
                System.out.println(me.getValue());
            }
            System.out.println("");

            Scanner in = new Scanner(System.in);
            try {
                System.out.println("Please enter a word or X to exit:");
                word = in.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Sorry, word not in dictionary");
            }

        } while (continueLoop);

    }

}
