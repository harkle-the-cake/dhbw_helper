package eu.boxwork.dhbw.examhelpers.rsa;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.params.RSAKeyGenerationParameters;
import org.bouncycastle.crypto.params.RSAKeyParameters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

public class ValuePicker {

    private static final BigInteger e = BigInteger.valueOf(3);

    public static void main(String[] args) throws IOException {
        int min = 0;
        int max = 100000;
        String path = "src/main/resources/primes"+max+".txt";
        File f = new File(path);
        BufferedReader br = new BufferedReader( new FileReader(f));
        br.mark(0);
        boolean exit = false;

        while(!exit)
        {
            // determine first prime
            int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
            String p = getLineAtPos(path, randomNum);

            // determine second prime
            int randomNumQ = ThreadLocalRandom.current().nextInt(min, max + 1);
            String q = getLineAtPos(path, randomNumQ);

            // test the primes
            String text = "Test";

            RSAKeyPairGenerator generator = new RSAKeyPairGenerator();
            generator.init(new RSAKeyGenerationParameters(e,
                    new SecureRandom(), 128, 80));
            AsymmetricCipherKeyPair keyPair = generator.generateKeyPair(new BigInteger(p),new BigInteger(q));

            RSAHelper handler = new RSAHelper();
            String enc = handler.encrypt(keyPair,text);
            String dec = handler.decrypt(keyPair,enc);

            if (dec.equals(text))
            {
                RSAKeyParameters publicKey =  (RSAKeyParameters) keyPair.getPublic();

                System.out.println("Modulus (p, BigInt)   : "+p);
                System.out.println("Modulus (q, BigInt)   : "+q);
                System.out.println("Modulus (public key, HEX)   : "+publicKey.getModulus().toString(16));
                System.out.println("Modulus (public key, BigInt): "+publicKey.getModulus());
                System.out.println("Chiffre: "+enc);
                exit=true;
            }
            else
            {
                br.reset();
            }
        }

        System.out.println("done");

    }

    private static String getLineAtPos(String fileName, int pos) throws IOException {
        File f = new File(fileName);
        BufferedReader br = new BufferedReader( new FileReader(f));
        String p = "";
        int cnt = 0;
        while (cnt<pos) { p = br.readLine(); cnt++; }
        br.close();
        return p;
    }
}
