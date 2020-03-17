package dsaa.lab_2;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Document{
    public final String name;
    public final OneWayLinkedList<Link> links;
    private static final Pattern linkPattern = Pattern.compile("link=[a-z]\\w*", Pattern.CASE_INSENSITIVE);

    public Document(String name, Scanner scan) {
        this.name = name;
        links = new OneWayLinkedList<>();
        load(scan);
    }

    public void load(Scanner scanner) {
        while(scanner.hasNext()) {
            String token = scanner.next();
            if(token.contains("eod")) {
                break;
            } else if (correctLink(token)) {
                String[] splittedLine = token.split("=");
                String link = "";
                if(splittedLine.length > 1) {
                    link = token.split("=")[1];
                }
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

        return result.toString().trim();
    }

}
