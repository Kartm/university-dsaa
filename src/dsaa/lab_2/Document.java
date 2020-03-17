package dsaa.lab_2;

import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Document{
    public String name;
    public OneWayLinkedList<Link> links;
    private static final Pattern linkPattern = Pattern.compile("link=[a-z]\\w*", Pattern.CASE_INSENSITIVE);

    public Document(String name, Scanner scan) {
        this.name = name;
        links = new OneWayLinkedList<Link>();
        load(scan);
    }
    public void load(Scanner scanner) {
        while(scanner.hasNext()) {
            String line = scanner.next();
            if(line.contains("eod")) {
                break;
            } else if (correctLink(line)) {
                String link = line.split("=")[1];
                links.add(new Link(link));
            }
        }
    }
    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
    private static boolean correctLink(String link) {
        Matcher matcher = linkPattern.matcher(link);
        return matcher.matches();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Document: " + this.name + "\n");
        for (Link link : links) {
            result.append(link.ref).append("\n");
        }

        return StringUtils.chomp(result.toString());
    }

}
