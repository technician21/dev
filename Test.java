package tst;

import java.util.Stack;

public class Test 
{
    public static void main( String[] args )
    {
        Test object = new Test();
        String abc = "{}()[]";
        object.parseString(abc, "()"); //true
        object.parseString(abc, "()[]{}"); //true
        object.parseString(abc, "(((([{[]}]))))"); //true;
        object.parseString(abc, "(|"); //false
        object.parseString(abc, "()([)]"); //false
        object.parseString(abc, "(()"); //false
        object.parseString(abc, "((()))["); //false
        object.parseString(abc, "(((])))["); //false
        object.parseString(abc, "(([))]"); //false
        object.parseString(abc, ""); //false
        object.parseString(abc, "["); //false
        object.parseString(abc, "]"); //false
        object.parseString(abc, "u"); //false
    }
    
    public boolean parseString(String abc, String input) {
        if(abc == null || "".equals(abc) || input == null || "".equals(input)) {
            System.out.println("result: " + false);
            return false;
        }
        Stack<Character> stack = new Stack<>();
        String openings = "";
        String closings = "";
        for(int i = 0; i < abc.length() - 1 ; i+=2) {
            openings += abc.charAt(i);
            closings += abc.charAt(i+1);
        }

        for(int i = 0; i < input.length(); i++) {
            boolean isCorrectSymbol = false;
            for(int j = 0; j < openings.length(); j++) {
                Character symbol = input.charAt(i);
                if(Character.compare(symbol, openings.charAt(j)) == 0) {
                    isCorrectSymbol = true;
                    stack.push(symbol);
                } else if(Character.compare(symbol, closings.charAt(j)) == 0) {
                    isCorrectSymbol = true;
                    if(!stack.isEmpty()) {
                        Character left = stack.pop();
                        if(!left.equals(openings.charAt(j))) {//закрывающая скобка должна соответствовать открывающей
                            System.out.println("result: " + false);
                            return false;
                        }
                    } else {//закрывающая скобка без открывающей
                        System.out.println("result: " + false);
                        return false;
                    }
                } 
            }
            if(!isCorrectSymbol) {
                System.out.println("result: " + false + " некорректный символ: " + input.charAt(i) + " index: " + i); //некорректный символ
                return false;
            }
        }
        if(!stack.isEmpty()) {
            System.out.println("result: " + false); //отсутствует закрывающая скобка
            return false;
        }
        else {
            System.out.println("result: " + true);
            return false;
        }
    }
}