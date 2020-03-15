package dsaa.lab_2;

import java.util.Scanner;

class Document{
    public String name;
    public OneWayLinkedList<Link> links;
    public Document(String name, Scanner scan) {
        // TODO
        load(scan);
    }
    public void load(Scanner scan) {
        //TODO
    }
    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
    private static boolean correctLink(String link) {
        return true;
    }

    @Override
    public String toString() {
        return null;
    }

}
