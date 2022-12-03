import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


public class Main {
    public static ArrayList<Bracket> init(String file_name) {
        String json_string;
        ArrayList<Bracket> brackets = new ArrayList<Bracket>();
        String left, right;
        try {
            json_string = Files.readString(Path.of(file_name));
        } catch (IOException e) {
            System.out.println("Error in config file");
            throw new RuntimeException(e);
        }
        JSONObject obj = new JSONObject(json_string);
        JSONArray arr = obj.getJSONArray("bracket");
        for (int i = 0; i < arr.length(); i++) {
            right = arr.getJSONObject(i).getString("right");
            left = arr.getJSONObject(i).getString("left");
            brackets.add(new Bracket(left, right));
        }
        return brackets;
    }

    public static int checkString(Bracket[] brackets, String string) {
        int error_position = -1;
        Boolean is_correct = true;
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < string.length() && is_correct; ++i) {
            for (Bracket bracket : brackets) {
                if (string.charAt(i) == bracket.getLeft()) {
                    stack.push(bracket.getRight());
                } else if (string.charAt(i) == bracket.getRight()){
                    if (stack.isEmpty()) {
                        is_correct = false;
                        error_position = i;
                    } else if (stack.peek() == string.charAt(i)) {
                        stack.pop();
                    } else {
                        is_correct = false;
                        error_position = i;
                    }
                }
            }
        }
        if (!stack.isEmpty() && is_correct) {
            error_position = string.length();
            is_correct = false;
        }
        return error_position;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(System.in);
        ArrayList<Bracket> brackets = new ArrayList<>();
        try {
            brackets = init("scbe.conf");
        } catch (RuntimeException e) {
            return;
        }
        Bracket[] array = brackets.toArray(new Bracket[0]);
        String string = s.nextLine();
        int error_position = checkString(array, string);

        if (error_position == -1) {
            System.out.println("String is correct");
        } else {
            System.out.println("Find error in " + (error_position + 1) + " position");
        }

    }


}