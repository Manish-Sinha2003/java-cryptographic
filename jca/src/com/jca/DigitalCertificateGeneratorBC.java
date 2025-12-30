package com.jca;
import java.math.BigInteger;
import java.security.*;
import java.util.Date;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import java.security.cert.X509Certificate;

public class DigitalCertificateGeneratorBC {
    public static void main(String[] args) throws Exception {
    	
    	Security.addProvider(new BouncyCastleProvider());
        // 1. Generate Key Pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();

        // 2. Certificate details
        X500Name issuer = new X500Name("CN=MyApp, O=MyCompany, C=IN");
        X500Name subject = issuer; // Self-signed
        BigInteger serial = BigInteger.valueOf(System.currentTimeMillis());
        Date notBefore = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        Date notAfter = new Date(System.currentTimeMillis() + (365 * 24L * 60 * 60 * 1000)); // 1 year validity

        // 3. Build the certificate
        X509v3CertificateBuilder certBuilder = new JcaX509v3CertificateBuilder(
                issuer,
                serial,
                notBefore,
                notAfter,
                subject,
                keyPair.getPublic()
        );

        // 4. Sign the certificate
        ContentSigner signer = new JcaContentSignerBuilder("SHA256withRSA").build(keyPair.getPrivate());
        X509Certificate cert = new JcaX509CertificateConverter()
                .setProvider("BC")
                .getCertificate(certBuilder.build(signer));

        // 5. Verify the certificate
        cert.verify(keyPair.getPublic());

        System.out.println("Generated Certificate:");
        System.out.println(cert);
    }
}
