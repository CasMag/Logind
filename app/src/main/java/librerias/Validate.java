package librerias;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    private static Pattern pat = null;
    private static Matcher mat = null;

    public static boolean isEmail(String email){

        pat = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        mat = pat.matcher(email);
        return mat.find();
    }
}
