package eu.boxwork.dhbw.examhelpers.rsa;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.params.RSAKeyGenerationParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class KeyGenerator {

    private static final long MAX = 5000;

    /**
     * this method we use to generate our test keys
     * */
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        eu.boxwork.dhbw.examhelpers.rsa.RSAKeyPairGenerator ownGenerator
                = new eu.boxwork.dhbw.examhelpers.rsa.RSAKeyPairGenerator();
        ownGenerator.init(new RSAKeyGenerationParameters(
                RSAStatics.e,
                new SecureRandom(),
                RSAStatics.KEYSIZE,
                RSAStatics.CERTAINTY));

        String path = "src/main/resources/primes10000.txt";
        File f = new File(path);
        if (!f.exists()) f.createNewFile();
        BufferedWriter bw = new BufferedWriter( new FileWriter(f));

        for(long i = 0; i < MAX; i++)
        {
            AsymmetricCipherKeyPair keyPair = ownGenerator.generateKeyPair();

            RSAPrivateCrtKeyParameters privateKey =  (RSAPrivateCrtKeyParameters) keyPair.getPrivate();

            System.out.println("p => '"+privateKey.getP()+"'");
            System.out.println("q => '"+privateKey.getQ()+"'");

            bw.write(String.valueOf(privateKey.getP())); bw.newLine();
            bw.write(String.valueOf(privateKey.getQ())); bw.newLine();

        }
        bw.close();
    }
}
