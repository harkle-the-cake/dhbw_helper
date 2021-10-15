package eu.boxwork.dhbw.examhelpers.rsa;

public class ExampleApplication {

    public static void main(String[] args) {
        RSAHelper helper = new RSAHelper();
        String p = "17594063653378370033"; // the prime 'p' to check, we can brute force and iterate throught the values
        String q = "15251864654563933379"; // the prime 'q' to check, we can brute force and iterate throught the values
        String publicKey = "268342277565109549360836262560222031507"; // the given public key
        String chiffre = "2d80afa14a65a7bf26636f97c89b43d5";

        System.out.println("P/Q fit to the public key: "+helper.isValid(p,q,publicKey));
        System.out.println("Decrypted text is: "+helper.decrypt(p,q,chiffre));
    }

}
