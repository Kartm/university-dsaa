package dsaa.lab_6;

import java.util.Arrays;
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
    private static final Pattern correctIdPattern =  Pattern.compile("^[a-z].*$", Pattern.CASE_INSENSITIVE);

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

    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)


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
        String retStr = "Document: " + name;
        int counter = 0;
        for (Link linkElem : link) {
            if (counter % 10 == 0)
                retStr += "\n";
            else
                retStr += " ";
            retStr += linkElem.toString();
            counter++;
        }
        return retStr;
    }

    public String toStringReverse() {
        String retStr = "Document: " + name;
        int counter = 0;
        ListIterator<Link> iter = link.listIterator();
        while (iter.hasNext())
            iter.next();
        while (iter.hasPrevious()) {
            if (counter % 10 == 0)
                retStr += "\n";
            else
                retStr += " ";
            retStr += iter.previous().toString();
            counter++;
        }
        return retStr;
    }

    public int[] getWeights() {
        int[] arr = new int[link.size()];
        int i = 0;
        for (Link linkElem : link) {
            arr[i++] = linkElem.weight;
        }
        return arr;
    }

    public static void showArray(int[] arr) {
        if (arr.length > 0) {
            System.out.print(arr[0]);
            for (int i = 1; i < arr.length; i++)
                System.out.print(" " + arr[i]);
        }
        System.out.println();
    }

    void bubbleSort(int[] arr) {
        showArray(arr);
        for (int begin = 0; begin < arr.length - 1; begin++) {
            for (int j = arr.length - 1; j > begin; j--)
                if (arr[j - 1] > arr[j])
                    swap(arr, j - 1, j);
            showArray(arr);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public void insertSort(int[] arr) {
        showArray(arr);
        for (int pos = arr.length - 2; pos >= 0; pos--) {
            int value = arr[pos];
            int i = pos + 1;
            while (i < arr.length && value > arr[i]) {
                arr[i - 1] = arr[i];
                i++;
            }
            arr[i - 1] = value;
            showArray(arr);
        }
    }

    public void selectSort(int[] arr) {
        showArray(arr);
        for (int border = arr.length; border > 1; border--) {
            int maxPos = 0;
            for (int pos = 0; pos < border; pos++)
                if (arr[maxPos] < arr[pos])
                    maxPos = pos;
            swap(arr, border - 1, maxPos);
            showArray(arr);
        }
    }

    private static void merge(int[] arr, int[] temp, int start, int mid, int end) {
        // merge arr[start, mid] and arr[mid + 1, end]
        int tempIndex = start, l = start, r = mid + 1;

        while (l <= mid && r <= end)
        {
            if (arr[l] < arr[r])
            {
                temp[tempIndex] = arr[l];
                l++;
            }
            else
            {
                temp[tempIndex] = arr[r];
                r++;
            }
            tempIndex++;
        }

        // copy remaining elements
        while (l < arr.length && l <= mid) {
            temp[tempIndex] = arr[l];
            tempIndex++;
            l++;
        }

        for (l = start; l <= end; l++) {
            arr[l] = temp[l];
        }
    }

    // increasing order
    public void iterativeMergeSort(int[] arr) {
        showArray(arr);

        int n = arr.length;
        int[] temp = Arrays.copyOf(arr, arr.length);
        for (int m = 1; m <= n-1; m *= 2)
        {
            // m = 1, i = 0, 2, 4, 6, 8
            // m = 2, i = 0, 4, 8, 16
            // m = 4, i = 0, 8, 16, 32

            for (int i = 0; i < n-1; i += 2*m)
            {
                int start = i;
                int mid = i + m - 1;
                int end = Integer.min(i + 2*m - 1, n - 1);

                merge(arr, temp, start, mid, end);
            }

            showArray(arr);
        }
    }

    private static int getMax(int arr[]) {
        if(arr.length == 0) {
            return 0;
        }

        int mx = arr[0];
        for (int i = 1; i < arr.length; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }

    static void countSort(int arr[], int exponent) {
        int[] outputArr = new int[arr.length];

        int[] digitCount = new int[10];
        Arrays.fill(digitCount, 0);

        // count occurrences of digits
        for (int number : arr) {
            int currentDigit = (number / exponent) % 10;
            digitCount[currentDigit]++;
        }

        // shift array of digit occurrences
        for (int i = 1; i < 10; i++) {
            digitCount[i] += digitCount[i - 1];
        }

        // sort the output array
        for (int i = arr.length - 1; i >= 0; i--) {
            int currentDigit = (arr[i] / exponent) % 10;
            outputArr[digitCount[currentDigit] - 1] = arr[i];
            digitCount[currentDigit]--; // decrease counter of digit
        }

        // assign the calculated values
        for (int i = 0; i < arr.length; i++) {
            arr[i] = outputArr[i];
        }
    }

    // increasing order
    public void radixSort(int[] arr) {
        showArray(arr);

        // assumption that the numbers are from 0 to 999
        // so digit indexes would be [0, 1, 2]
        for (int digitIndex = 0; digitIndex <= 2; digitIndex++) {
            countSort(arr, (int) Math.pow(10, digitIndex));
            showArray(arr);
        }
    }
}
