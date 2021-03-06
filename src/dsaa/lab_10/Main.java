package dsaa.lab_10;

import java.util.*;

public class Main {

    static Scanner scan; // for input stream

    private static Document findInSortedSet(SortedSet<Document> set, String nameToFind) {
        for(Document doc: set) {
            if(doc.name.equals(nameToFind)) {
                return doc;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("START");
        scan = new Scanner(System.in);
        SortedSet<Document> sortedSet = new TreeSet<>();
        Document currentDoc = null;
        boolean halt = false;
        while (!halt) {
            String line = scan.nextLine();
            // empty line and comment line - read next line
            if (line.length() == 0 || line.charAt(0) == '#')
                continue;
            // copy line to output (it is easier to find a place of a mistake)
            System.out.println("!" + line);
            String word[] = line.split(" ");
            //getdoc name - change document to name
            if (word[0].equalsIgnoreCase("getdoc") && word.length == 2) {
                // currentDoc = (Document) sortedSet.get(word[1]);
                currentDoc = findInSortedSet(sortedSet, word[1]);
                continue;
            }

            // ld documentName
            if (word[0].equalsIgnoreCase("ld") && word.length == 2) {
                if (Document.isCorrectId(word[1])) {
                    if(findInSortedSet(sortedSet, word[1]) != null) {
                        System.out.println("error");
                        continue;
                    }

                    currentDoc = new Document(word[1], scan);
                    sortedSet.add(currentDoc);
                } else
                    System.out.println("incorrect ID");
                continue;
            }
            // ha
            if (word[0].equalsIgnoreCase("ha") && word.length == 1) {
                halt = true;
                continue;
            }
            // clear
            if (word[0].equalsIgnoreCase("clear") && word.length == 1) {
                if (currentDoc != null)
                    currentDoc.link.clear();
                else
                    System.out.println("no current document");
                continue;
            }
            // show
            // it depends of the representation so it will be NOT in tests
            if (word[0].equalsIgnoreCase("show") && word.length == 1) {
                if (currentDoc != null)
                    System.out.println(currentDoc.toString());
                else
                    System.out.println("no current document");
                continue;
            }
            // size
            if (word[0].equalsIgnoreCase("size") && word.length == 1) {
                if (currentDoc != null)
                    System.out.println(currentDoc.link.size());
                else
                    System.out.println("no current document");
                continue;
            }
            // add str
            if (word[0].equalsIgnoreCase("add") && word.length == 2) {
                if (currentDoc != null) {
                    // changed this line to reuse link creation
                    Link link = Document.createLink("link=" + word[1]);
                    if (link == null || link.ref == null || link.weight < 0 || !Document.isCorrectId(word[1]))
                        System.out.println("error");
                    else {
                        currentDoc.link.put(link.ref, link);
                        // todo ensure it overwrites it
                        System.out.println("true");
                    }
                } else
                    System.out.println("no current document");
                continue;
            }
            // get str
            if (word[0].equalsIgnoreCase("get") && word.length == 2) {
                if (currentDoc != null) {
                    Link l = currentDoc.link.get(word[1]);
                    if (l != null && l.ref != null && l.weight >= 0) {
                        System.out.println(l);
                    } else {
                        System.out.println("error");
                    }
                } else
                    System.out.println("no current document");
                continue;
            }
            // rem str
            if (word[0].equalsIgnoreCase("rem") && word.length == 2) {
                if (currentDoc != null) {
                    Link l = currentDoc.link.remove(word[1]);
                    if (l != null && l.ref != null && l.weight >= 0) {
                        // write the removed link
                        System.out.println(l);
                    } else {
                        System.out.println("error");
                    }
                } else
                    System.out.println("no current document");

                continue;
            }

            // bfs str
            if (word[0].equalsIgnoreCase("bfs") && word.length == 2) {
                Graph graph = new Graph(sortedSet);

                String str = graph.bfs(word[1]);
                if (str != null) {
                    System.out.println(str);
                } else {
                    System.out.println("error");
                }
                continue;
            }
            // dfs str
            if (word[0].equalsIgnoreCase("dfs") && word.length == 2) {
                Graph graph = new Graph(sortedSet);

                String str = graph.dfs(word[1]);
                if (str != null) {
                    System.out.println(str);
                } else {
                    System.out.println("error");
                }
                continue;
            }
            // cc
            if (word[0].equalsIgnoreCase("cc") && word.length == 1) {
                Graph graph = new Graph(sortedSet);

                int str = graph.connectedComponents();
                System.out.println(str);
                continue;
            }
            System.out.println("Wrong command");
        }
        System.out.println("END OF EXECUTION");
        scan.close();
    }

}