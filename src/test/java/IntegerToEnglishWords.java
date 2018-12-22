/**
 * convert integer to English words
 * like:
 *  123456 -> one hundred twenty three thousand four hundred fifty six
 */
public class IntegerToEnglishWords {
    public static void main(String[] args) {
        int num = (int) Math.ceil(Math.random() * Math.pow(10,10));
        System.out.println("number:" + num);
        System.out.println("en:"+convertToEnglishWords(num));
    }

    /**
     * to single word
     * @param num
     */
    public static String one(int num){
        switch (num){
            case 0:
                return "zero";
            case 1:
                return "one";
            case 2:
                return "two";
            case 3:
                return "three";
            case 4:
                return "four";
            case 5:
                return "five";
            case 6:
                return "six";
            case 7:
                return "seven";
            case 8:
                return "eight";
            case 9:
                return "nine";
            default:
                return "";
        }
    }

    /**
     * convert two number which is no more than 20 to words
     * @param num
     * @return
     */
    public static String twoNoMoreThan20(int num){
        switch (num){
            case 10:
                return "ten";
            case 11:
                return "eleven";
            case 12:
                return "twelve";
            case 13:
                return "thirteen";
            case 14:
                return "fourteen";
            case 15:
                return "fifteen";
            case 16:
                return "sixteen";
            case 17:
                return "seventeen";
            case 18:
                return "eightteen";
            case 19:
                return "nineteen";
            case 20:
                return "tweenty";
            default:
                return "";

        }
    }

    /**
     * convert two number which is more than 20 to words
     * @param num
     * @return
     */
    public static String two(int num){
        int head = num / 10;
        switch (head){
            case 2:
                return "twenty";
            case 3:
                return "thirty";
            case 4:
                return "forty";
            case 5:
                return "fifty";
            case 6:
                return "sixty";
            case 7:
                return "seventy";
            case 8:
                return "eighty";
            case 9:
                return "ninety";
            default:
                return "";
        }

    }

    /**
     * handle three number
     * @param num
     * @return
     */
    public static String three(int num){
        int headNum = num / 100;
        int rest = num - headNum * 100;
        int tail = rest - rest / 10 * 10;
        return (headNum == 0 ? "" :one(headNum) + " Hundred ") + (rest > 20 ? two(rest) : twoNoMoreThan20(rest)) + " " + one(tail);
    }

    public static String convertToEnglishWords(int num){
        int head_billion = num / 1000000000;
        int head_million = num / 1000000;
        int head_thousand = num /1000;
        if(head_billion != 0){
            int rest_million = (num - head_billion * 1000000000) / 1000000;
            int rest_thousand = (num - head_billion * 1000000000 - rest_million * 1000000) / 1000;
            int rest = num - head_billion * 1000000000 - rest_million * 1000000 - rest_thousand * 1000;
            return three(head_billion) + " billion " + (rest_million == 0 ? "" : three(rest_million) + " million ") + (rest_thousand == 0 ? "" : three(rest_thousand) + " thousand ") + three(rest);
        } else if(head_million != 0){
            int rest_thousand = (num - head_million * 1000000) / 1000;
            int rest = num - head_million * 1000000 - rest_thousand * 1000;
            return three(head_million) + " million " + (rest_thousand == 0 ? "" : three(rest_thousand) + " thousand ") + three(rest);
        } else if(head_thousand != 0){
            int rest = num - head_thousand * 1000;
            return rest + " thousand " + three(rest);
        } else {
            return three(num);
        }
    }
}

