package kurs002;

public class Utils {
    public static int getDigit(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                sum += Character.getNumericValue(c);
            }
        }
        return sum;
    }

    public static void myPrint(String s) {
        if(s == null) return;
        if (s.length() < 1) return;
        int length = s.length();
        final int lineLength = 65;
        StringBuilder sb = new StringBuilder(s);
        StringBuilder forPrint = new StringBuilder();
        for(int i = 0, j = 0; i < length; i++, j++){
            forPrint.append(sb.charAt(i));
            if((j > lineLength && sb.charAt(i) == ' ') || j > lineLength + 10){
                forPrint.append("\n");
                j = 0;
            }
        }
        System.out.println(forPrint);
    }

    public static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
