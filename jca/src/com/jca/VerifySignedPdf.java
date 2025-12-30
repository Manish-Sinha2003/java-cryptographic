package com.jca;

import com.itextpdf.kernel.pdf.PdfDictionary;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.signatures.*;

import java.io.FileInputStream;
import java.security.cert.Certificate;
import java.util.List;

public class VerifySignedPdf {
    public static void main(String[] args) throws Exception {
        String pdfPath = "signed_document.pdf";

        PdfDocument pdfDoc = new PdfDocument(new PdfReader(pdfPath));
        SignatureUtil signUtil = new SignatureUtil(pdfDoc);

        List<String> sigNames = signUtil.getSignatureNames();
        for (String name : sigNames) {
        	PdfDictionary dict = signUtil.getSignatureDictionary(name);
        	System.out.println("Contents: " + dict.getAsString(PdfName.Contents));
        	System.out.println("Cert: " + dict.getAsString(PdfName.Cert));
        	System.out.println("SubFilter: " + dict.getAsName(PdfName.SubFilter));

            PdfPKCS7 pkcs7 = signUtil.readSignatureData(name);
            System.out.println("Signature name: " + name);
            System.out.println("Is the signature covers whole document: " + signUtil.signatureCoversWholeDocument(name));
            System.out.println("Document revision: " + signUtil.getRevision(name) + " of " + signUtil.getTotalRevisions());
            System.out.println("Integrity check OK? " + pkcs7.verifySignatureIntegrityAndAuthenticity());

            Certificate[] certs = pkcs7.getSignCertificateChain();
            System.out.println("Signer: " + pkcs7.getSigningCertificate().toString());
        }

        pdfDoc.close();
    }
}
