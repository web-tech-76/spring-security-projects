package provider02.api.util;

import java.util.Random;

public class OtpUtil {

    public int generateIntOtp(int maxBound)  {
        Random random = new Random();
        return random.nextInt(maxBound);
    }

}
