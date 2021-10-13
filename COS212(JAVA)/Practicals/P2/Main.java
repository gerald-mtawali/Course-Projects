/**
 * Name: Gerald Mtawali 
 * Student Number:	u15264999
 */

public class Main {
    public static void main(String[] args) {
        OrganisingList list = new OrganisingList();

        list.insert(5);
        list.insert(2);
        list.insert(9);
        list.insert(5);
        list.insert(20);

        System.out.println(list.toStringKeysOnly());
        System.out.println(list.toString());

        System.out.println(list.getData(9));
        System.out.println(list.toString());

        list.delete(9);
        System.out.println(list.toString());

        System.out.println(list.contains(2));
        
        /*
        Expected output:

        5, 2, 9, 20
        [K: 5, D: 359] [K: 2, D: -179] [K: 9, D: 241] [K: 20, D: 20]
        241
        [K: 9, D: 213] [K: 5, D: 111] [K: 2, D: 24] [K: 20, D: 20]
        [K: 5, D: 111] [K: 2, D: 24] [K: 20, D: 20]
        true

        */
    }
}
