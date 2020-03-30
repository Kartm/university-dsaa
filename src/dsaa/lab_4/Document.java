package dsaa.lab_4;

import java.util.ListIterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Document {
    public String name;
    public TwoWayCycledOrderedListWithSentinel<Link> link;
    private static final Pattern linkPattern = Pattern.compile("(link=[\\w]+(\\([0-9]+\\))*)", Pattern.CASE_INSENSITIVE);
    private static final Pattern linkWithWeightPattern = Pattern.compile("(link=[\\w]+(\\([0-9]+\\))+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern insideParenthesesPattern = Pattern.compile("\\(([^)]+)\\)", Pattern.CASE_INSENSITIVE);
    private static final Pattern correctIdPattern = Pattern.compile("^[a-zA-Z]+$", Pattern.CASE_INSENSITIVE);

    public Document(String name, Scanner scan) {
        this.name = name.toLowerCase();
        link = new TwoWayCycledOrderedListWithSentinel<Link>();
        load(scan);
    }

    public void load(Scanner scanner) {
        while (scanner.hasNext()) {
            String token = scanner.next();
            if (token.contains("eod")) {
                break;
            } else {
                Link linkFromToken = createLink(token);
                if (linkFromToken != null) {
                    link.add(linkFromToken);
                }
            }
        }
    }

    public static boolean isCorrectId(String id) {
        Matcher idMatcher = correctIdPattern.matcher(id);
        return idMatcher.matches();
    }

    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
    // and eventually weight in parenthesis
    static Link createLink(String link) {
        Matcher linkMatcher = linkPattern.matcher(link);
        Matcher linkWithWeightMatcher = linkWithWeightPattern.matcher(link);
        if (linkMatcher.matches()) {
            String name = link.replace("link=", "");
            if (linkWithWeightMatcher.matches()) {
                Matcher parenthesesMatcher = insideParenthesesPattern.matcher(name);
                if (parenthesesMatcher.find()) {
                    String rawWeight = parenthesesMatcher
                            .group(1)
                            .replace("(", "")
                            .replace(")", "");
                    int weight = Integer.parseInt(rawWeight);
                    name = name.replace("(" + weight + ")", "");
                    return new Link(name.toLowerCase(), weight);
                }
            } else {
                return new Link(name);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String retStr = "Document: " + name + "\n";
        ListIterator<Link> iter = link.listIterator();
        int i = 0;
        while (iter.hasNext()) {
            retStr += iter.next();
            if(i % 9 != 0 || i == 0) {
                retStr += " ";
            } else {
                retStr += "\n";
            }
            i++;
        }
        return retStr.trim();
    }

    public String toStringReverse() {
        String retStr = "Document: " + name + '\n';
        ListIterator<Link> iter = link.listIterator();

        while (iter.hasNext())
            iter.next();

        int i = 0;
        while (iter.hasPrevious()) {
            retStr += iter.previous();
            if(i % 9 != 0 || i == 0) {
                retStr += " ";
            } else {
                retStr += "\n";
            }
            i++;
        }
        return retStr.trim();
    }
}
