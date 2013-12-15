import javax.crypto.Cipher;
import java.security.NoSuchAlgorithmException;
import java.util.Set;
 
public class UnlimitedJCETest extends Base {
    public static void main (String[] args) {
        new UnlimitedJCETest().dispatch(args);
    }

    protected void run (Set<Variant> variants) {
        int allowedKeyLength = 0;

        try {
            allowedKeyLength = Cipher.getMaxAllowedKeyLength("AES");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("AES cipher not found");
            System.exit(1);
        }

        if (variants.contains(Variant.limited_strength_jce)) {
        if (allowedKeyLength > 128)
            throw fail("The allowed key length for AES is too high: " + allowedKeyLength);
        } else {
        if (allowedKeyLength <= 128)
            throw fail("The allowed key length for AES is too low: " + allowedKeyLength);
        }

        pass("The allowed key length for AES is " + allowedKeyLength);
    }
}
