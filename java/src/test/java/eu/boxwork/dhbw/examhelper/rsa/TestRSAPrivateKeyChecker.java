package eu.boxwork.dhbw.examhelper.rsa;

import eu.boxwork.dhbw.examhelpers.rsa.*;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.RSAKeyPairGenerator;
import org.bouncycastle.crypto.params.RSAKeyGenerationParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.security.SecureRandom;
import static org.junit.jupiter.api.Assertions.*;

public class TestRSAPrivateKeyChecker {


    @BeforeAll
    public static void init()
    {

    }

    @Test
    public void testRSAValidKeyBC() {
        RSAHelper checker = new RSAHelper();

        RSAKeyPairGenerator generator = new RSAKeyPairGenerator();
        generator.init(new RSAKeyGenerationParameters(RSAStatics.e,
                new SecureRandom(), 128, 80));
        AsymmetricCipherKeyPair keyPair = generator.generateKeyPair();

        RSAPrivateCrtKeyParameters privateKey =  (RSAPrivateCrtKeyParameters) keyPair.getPrivate();
        assertTrue(checker.isValid(privateKey.getP(),privateKey.getQ(),privateKey.getModulus()));
    }

    @Test
    public void testRSAValidKeyGeneration() {
        eu.boxwork.dhbw.examhelpers.rsa.RSAKeyPairGenerator ownGenerator =
                new eu.boxwork.dhbw.examhelpers.rsa.RSAKeyPairGenerator();
        RSAHelper checker = new RSAHelper();

        ownGenerator.init(new RSAKeyGenerationParameters(RSAStatics.e,
                new SecureRandom(), 1024, 80));

        BigInteger p = new BigInteger("17594063653378370033");
        BigInteger q = new BigInteger("15251864654563933379");

        AsymmetricCipherKeyPair keyPair = ownGenerator.generateKeyPair(p,q);

        RSAPrivateCrtKeyParameters privateKey =  (RSAPrivateCrtKeyParameters) keyPair.getPrivate();
        System.out.println("Modulus: "+privateKey.getModulus());
        assertTrue(checker.isValid(p,q,privateKey.getModulus()));
    }

    @Test
    public void testStength() {
        RSAHelper checker = new RSAHelper();
        BigInteger p = BigInteger.valueOf(985703l );
        BigInteger q = BigInteger.valueOf(1013237l);

        assertTrue(checker.isStrengthValid(p,q,40));
    }

    @Test
    public void testCryptRoundtrip()
    {
        eu.boxwork.dhbw.examhelpers.rsa.RSAKeyPairGenerator ownGenerator
                = new eu.boxwork.dhbw.examhelpers.rsa.RSAKeyPairGenerator();
        ownGenerator.init(new RSAKeyGenerationParameters(RSAStatics.e,
                new SecureRandom(), 128, 80));

        RSAHelper checker = new RSAHelper();
        BigInteger p = BigInteger.valueOf(125424621163l);

        String data = "Test 123";
        AsymmetricCipherKeyPair keyPair = ownGenerator.generateKeyPair(p);

        RSAPrivateCrtKeyParameters privateKey =  (RSAPrivateCrtKeyParameters) keyPair.getPrivate();

        System.out.println("p => '"+privateKey.getP()+"'");
        System.out.println("q => '"+privateKey.getQ()+"'");

        String encrypted = checker.encrypt(keyPair,data);

        assertNotNull(encrypted);
        assertNotEquals("",encrypted);

        String decrypted = checker.decrypt(keyPair,encrypted);
        assertEquals(data,decrypted);
    }

    @Test
    public void testCryptRoundtrip2()
    {
        eu.boxwork.dhbw.examhelpers.rsa.RSAKeyPairGenerator ownGenerator
                = new eu.boxwork.dhbw.examhelpers.rsa.RSAKeyPairGenerator();
        ownGenerator.init(new RSAKeyGenerationParameters(RSAStatics.e,
                new SecureRandom(), 128, 80));

        RSAHelper checker = new RSAHelper();
        BigInteger p = new BigInteger("17849527222525822403");
        BigInteger q = new BigInteger("16350234166362999863");

        String data = "Test 123";
        AsymmetricCipherKeyPair keyPair = ownGenerator.generateKeyPair(p,q);

        RSAPrivateCrtKeyParameters privateKey =  (RSAPrivateCrtKeyParameters) keyPair.getPrivate();

        System.out.println("p => '"+privateKey.getP()+"'");
        System.out.println("q => '"+privateKey.getQ()+"'");

        String encrypted = checker.encrypt(keyPair,data);

        assertNotNull(encrypted);
        assertNotEquals("",encrypted);

        String decrypted = checker.decrypt(keyPair,encrypted);
        assertEquals(data,decrypted);
    }
}
