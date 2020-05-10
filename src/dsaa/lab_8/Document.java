package dsaa.lab_8;

import java.math.BigInteger;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Document implements IWithName{
    public String name;
    public BST<Link> link;
    private static final Pattern linkPattern = Pattern.compile("(link=[\\w]+(\\([0-9]+\\))*)", Pattern.CASE_INSENSITIVE);
    private static final Pattern linkWithWeightPattern = Pattern.compile("(link=[\\w]+(\\([0-9]+\\))+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern insideParenthesesPattern = Pattern.compile("\\(([^)]+)\\)", Pattern.CASE_INSENSITIVE);
    private static final Pattern correctIdPattern =  Pattern.compile("^[a-z].*$", Pattern.CASE_INSENSITIVE);
    
    public Document(String name) {
        this.name = name.toLowerCase();
        link = new BST<Link>();
    }

    public Document(String name, Scanner scan) {
        this.name = name.toLowerCase();
        link = new BST<Link>();
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

    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
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
        String retStr="Document: "+name+"\n";
        retStr+=link.toStringInOrder();
        return retStr;
    }

    public String toStringPreOrder() {
        String retStr="Document: "+name+"\n";
        retStr+=link.toStringPreOrder();
        return retStr;
    }

    public String toStringPostOrder() {
        String retStr="Document: "+name+"\n";
        retStr+=link.toStringPostOrder();
        return retStr;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Document other = (Document) obj;
        if (other.getName() == null) {
            return false;
        }
        return this.getName().equals(other.getName());
    }

    @Override
    public int hashCode() {
        // todo overflow
        int[] sequence = new int[]{7,11,13,17,19};

        int resultHashCode = this.name.charAt(0);

        for(int i = 0; i < this.name.length() - 1; i++) {
            int sequenceNumber = sequence[i % sequence.length];

            resultHashCode *= sequenceNumber;
            resultHashCode += this.name.charAt(i + 1);

        }

        return resultHashCode;
    }

    public BigInteger hashCodeBigInt() {
        int[] sequence = new int[]{7,11,13,17,19};

        BigInteger resultHashCode = BigInteger.valueOf((int)this.name.charAt(0));

        for(int i = 0; i < this.name.length() - 1; i++) {
            int sequenceNumber = sequence[i % sequence.length];

            resultHashCode = resultHashCode.multiply(BigInteger.valueOf(sequenceNumber));
            resultHashCode = resultHashCode.add(BigInteger.valueOf((int)this.name.charAt(i + 1)));

        }

        return resultHashCode;
    }
}
