import java.io.*;
import java.util.*;

public class Main2 {

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//        try {
//            File file = new File("./Brackets_testcase/1.input.txt");
//            br = new BufferedReader(new FileReader(file));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        int n = Integer.parseInt(br.readLine());

        String[] questions = new String[n];
        for (int i=0; i<n ; i++) {
            questions[i] = br.readLine();
        }

        StringBuilder sb = new StringBuilder();
        for (String string: questions) {
            sb.append(getAnswer(string));
            sb.append("\n");
        }

        System.out.print(sb.toString());
    }

    private static int getAnswer(String string) {

        // 괄호가 홀수개면 무조건 예외.
        if (string.length()%2==1) return 0;

        char[] charArr = string.toCharArray();

        Stack<int[]> answerStack = new Stack<>();
        answerStack.push(new int[] {' ', 0});

        Character previous = null;

        for (char current : charArr) {
            if (previous == null) {
                if  (current == '(' || current == '{' || current == '[') {
                    previous = current;
                } else {
                    int[] lastAnswerStack = answerStack.pop();

                    if (lastAnswerStack[0]=='(' && current==')') {
                        if (addToLastAnswerStack(answerStack, lastAnswerStack[1])) previous=null; else return 0;
                    } else if (lastAnswerStack[0]=='{' && current=='}') {
                        if (addToLastAnswerStack(answerStack, lastAnswerStack[1]*2)) previous=null; else return 0;
                    } else if (lastAnswerStack[0]=='[' && current==']') {
                        if (addToLastAnswerStack(answerStack, lastAnswerStack[1]*3)) previous=null; else return 0;
                    } else {
                        return 0;
                    }
                }
            } else {
                if (previous=='(' && current==')') {
                    if (addToLastAnswerStack(answerStack, 1)) previous=null; else return 0;
                } else if (previous=='{' && current=='}') {
                    if (addToLastAnswerStack(answerStack, 2)) previous=null; else return 0;
                } else if (previous=='[' && current==']') {
                    if (addToLastAnswerStack(answerStack, 3)) previous=null; else return 0;
                } else if (current == '(' || current == '{' || current == '[') {
                    answerStack.push(new int[] {previous, 0});
                    previous = current;
                }
            }
        }

        if (answerStack.size()==1) {
            return answerStack.peek()[1];
        } else {
            return 0;
        }
    }

    private static boolean addToLastAnswerStack(Stack<int[]> answerStack, int addNumber) {
        if (answerStack.isEmpty()) {
            return false;
        } else {
            int[] lastAnswerStack = answerStack.pop();
            answerStack.push(new int[] {lastAnswerStack[0], (lastAnswerStack[1]+addNumber)%100000000});
            return true;
        }
    }
}
