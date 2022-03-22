/*Write a program for simple RSA algorithm to encrypt and decrypt the data.
Algorithm 
1. Generate two large random primes, P and Q, of approximately equal size. 
2. Compute N = P x Q 
3. Compute Z = (P-1) x (Q-1). 
4. Choose an integer E, 1 < E < Z, such that GCD (E, Z) = 1 
5. Compute the secret exponent D, 1 < D < Z, such that E x D ≡ 1 (mod Z) 
6. The public key is (N, E) and the private key is (N, D). */

package com.WARRIOR;
import java.io.*;
import java.math.*;
import java.security.PrivateKey;
import java.util.*;

public class RSA {
    private BigInteger p,q,N,phi,e,d;
    private int bitlength = 1024;
    private Random r;
    public RSA(){
        r = new Random();
        p = BigInteger.probablePrime(bitlength, r);
        q = BigInteger.probablePrime(bitlength, r);
        System.out.println("Prime number P is : " + p);
        System.out.println("Prime number q is : " + q);
        N = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitlength/2, r);
        while(phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi)<0){
            e.add(BigInteger.ONE);
        }
        System.out.println("Public Key is " + e);
        d = e.modInverse(phi);
        System.out.println("Private Key is " + d);
    }
    public RSA(BigInteger e, BigInteger d, BigInteger N){
        this.e = e;
        this.d = d;
        this.N = N;
    }

    public static void main(String[] args)throws IOException {
        RSA rsa = new RSA();
        DataInputStream in = new DataInputStream(System.in);
        String testString;
        System.out.println("Enter the PlainText: ");
        testString = in.readLine();
        System.out.println("Encrypting String" + testString);
        System.out.println("string in bytes: " + bytesToString(testString.getBytes()));
        byte[] encrypted = rsa.encrypt(testString.getBytes());
        byte[] decrypted = rsa.decrypt(encrypted);
        System.out.println("Decrypting Bytes: " + bytesToString(decrypted));
        System.out.println("Decrypting String: " + new String((decrypted)));
    }
    private static String bytesToString(byte[] encrypted){
        String test = " ";
        for(byte b: encrypted){
            test += Byte.toString(b);
        }
        return test;
    }
    public byte[] encrypt(byte[] message){
        return(new BigInteger(message)).modPow(e,N).toByteArray();
    }
    public byte[] decrypt(byte[] message){
        return(new BigInteger(message)).modPow(d,N).toByteArray();
    }
}




