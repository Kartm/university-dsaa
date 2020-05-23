package dsaa.lab_10;

import java.util.Scanner;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document implements IWithName {
    public String name;
    // TODO? You can change implementation of Link collection
    public SortedMap<String, Link> link;

    private static final Pattern linkPattern = Pattern.compile("(link=[\\w]+(\\([0-9]+\\))*)", Pattern.CASE_INSENSITIVE);
    private static final Pattern linkWithWeightPattern = Pattern.compile("(link=[\\w]+(\\([0-9]+\\))+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern insideParenthesesPattern = Pattern.compile("\\(([^)]+)\\)", Pattern.CASE_INSENSITIVE);
    private static final Pattern correctIdPattern = Pattern.compile("^[a-z].*$", Pattern.CASE_INSENSITIVE);

    public Document(String name) {
        this.name = name.toLowerCase();
        link = new TreeMap<String, Link>();
    }

    public Document(String name, Scanner scan) {
        this.name = name.toLowerCase();
        link = new TreeMap<String, Link>();
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
                    // todo what's the key here?
                    link.put(linkFromToken.ref, linkFromToken);
                }
            }
        }
    }

    public static boolean isCorrectId(String id) {
        Matcher idMatcher = correctIdPattern.matcher(id);
        return idMatcher.matches();
    }

    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
    static Link createLink(String link) {
        Matcher linkMatcher = linkPattern.matcher(link);
        Matcher linkWithWeightMatcher = linkWithWeightPattern.matcher(link);
        if (linkMatcher.matches()) {
            String name = link.toLowerCase().replace("link=", "");
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