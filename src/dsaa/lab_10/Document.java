package dsaa.lab_10;

import java.util.Scanner;
import java.util.*;

public class Document implements IWithName {
    public String name;
    // TODO? You can change implementation of Link collection
    public SortedMap<String, Link> link;

    public Document(String name) {
        this.name = name.toLowerCase();
        link = new TreeMap<String, Link>();
    }

    public Document(String name, Scanner scan) {
        this.name = name.toLowerCase();
        link = new TreeMap<String, Link>();
        load(scan);
    }

    public void load(Scanner scan) {
        //TODO
    }

    public static boolean isCorrectId(String id) {
        //TODO
        return false;
    }

    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
    static Link createLink(String link) {
        //TODO
        return null;
    }

    @Override
    public String toString() {
        String retStr = "Document: " + name + "\n";
        //TODO?
        retStr += link;
        return retStr;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String getName() {
        return name;
    }
}