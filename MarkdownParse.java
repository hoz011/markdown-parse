// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        String[] linkConventions = {"[", "](", ")"};
        int i = 0;
        for (String s: linkConventions) {
            if (markdown.contains(s)) {i++;}
        }
        if (i == 3) {
            int index = markdown.indexOf("[", currentIndex);
            int nextOpenBracket;
            if (String.valueOf(markdown.charAt(index-1)).equals("!")) {
                nextOpenBracket = markdown.indexOf("[", index+1);
                if (nextOpenBracket == -1) {return toReturn;}
            } else {
                nextOpenBracket = index;
            }
            while(currentIndex < markdown.length()) {
                System.out.println(String.valueOf(currentIndex) + ", ");
                int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
                int openParen = markdown.indexOf("(", nextCloseBracket);
                int closeParen = markdown.indexOf(")", openParen);
                toReturn.add(markdown.substring(openParen + 1, closeParen));
                currentIndex = closeParen + 1;
                index = markdown.indexOf("[", currentIndex);
                if (index != -1) {
                    if (String.valueOf(markdown.charAt(index-1)).equals("!")) {
                        nextOpenBracket = markdown.indexOf("[", index);
                    } else {
                        nextOpenBracket = index;
                    }
                } else { break; }
                if (nextOpenBracket == -1) { break; }
                System.out.println(String.valueOf(currentIndex) + ", ");
            }
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}