package provider02.api.util;

public class UserType {

    public int userType(String userString) {
        int type = 0;
        try {
            if (null != userString && userString.contains("@")) {
                type = 1;
            } else if (null != userString) {
                long phone = Long.parseLong(userString);
                type =2;
            }

        } catch (Exception e) {
            type = 0 ;
        }
            return type;
    }
}
