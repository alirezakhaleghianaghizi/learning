package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Algorithm {
    LOGIN("\\s*login\\s+(\\d+)\\s+(\\d+)\\s*$"),
    SIGNIN("^(?i)\\s*signin\\s+(\\w+)\\s+(\\d+)\\s*$"),
    LISTR("^(?i)\\s*ls\\s+-r\\s*$"),
    LISTI("^(?i)\\s*ls\\s+-i\\s*$"),
    LISTN("^(?i)\\s*ls\\s+-n\\s*$"),
    ORDERGOOD("^(?i)\\s*order\\s+(\\d+)\\s+-c\\s+(\\d+\\.?\\d*)\\s+(\\w+)\\s*$"),
    DELETORDER("^(?i)\\s*order\\s+-d\\s+(\\d+)\\s*$"),
    LOGOUT("^(?i)\\s*logout\\s*$"),
    LISTO("^(?i)\\s*ls\\s+-o\\s*$"),
    CHECKORDER("^(?i)\\s*checkout\\s+(\\d+)\\s*$"),
    LISTHO("^(?i)\\s*ls\\s+-ho\\s*$"),
    ADDGOOD("^(?i)\\s*add\\s+-n\\s+(\\w+)\\s+-c\\s+(\\d+\\.?\\d*)\\s+(\\w+)\\s+-sp\\s+(\\d+\\.?\\d*)\\s+-bp\\s+(\\d+\\.?\\d*)\\s*$"),
    REMOVEGOOD("^(?i)\\s*remove\\s+-c\\s+(\\w+)\\s*$"),
    EDDITGOODNAME("^(?i)\\s*edit\\s+(\\d+)\\s+-n\\s+(\\w+)\\s*$"),
    EDDITGOODAMOUNT("^(?i)\\s*edit\\s+(\\d+)\\s+-n\\s+(\\w+)\\s+-c\\s+(\\d+\\.?\\d*)\\s*$"),
    EDDITGOODSELLBUYAMOUNT("^(?i)\\s*edit\\s+(\\d+)\\s+-sp\\s+(\\d+\\.?\\d*)\\s+-bp\\s+(\\d+\\.?\\d*)\\s+-c\\s+(\\d+\\.?\\d*)\\s*$"),
    CALCULATEPALL("^(?i)\\s*calc\\s+-p\\s*$"),
    CALCULATEPGOOD("^(?i)\\s*calc\\s+-p\\s+(\\d+)\\s*$"),
    CALCULATESALL("^(?i)\\s*calc\\s+-s\\s*$"),
    CALCULATESGOOD("^(?i)\\s*calc\\s+-s\\s+(\\d+)\\s*$")
    ;
    final Pattern inputPattern;
    Algorithm(String s) {
        this.inputPattern=Pattern.compile(s);
    }
    public Matcher inputMatcher(String input){

        return this.inputPattern.matcher(input);
    }
}
