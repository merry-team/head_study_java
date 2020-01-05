package com.sharingcard.mongodb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.aspose.words.Document;
import com.aspose.words.HtmlSaveOptions;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

public class CrackSample {

    public static void main(String[] args) throws Exception {
        String baseDir = "E:\\User\\Neteye\\Neteye\\docment\\daily\\201906\\纸笔\\";
        //doc2pdf(baseDir + "1.doc", baseDir + "菁优教育题库调用接口说明.pdf");
        pdf2html(baseDir + "菁优教育题库调用接口说明.pdf", baseDir + "菁优教育题库调用接口说明.html");
    }

    /**
     * 使用Aspose.Words前都要验证License 若不验证则转化出的pdf文档有水印
     * @return
     * @throws Exception
     */
	public static boolean checkLicense() throws Exception {
        boolean result = false;
        try {
            InputStream is = com.aspose.words.Document.class
                    .getResourceAsStream("/com.aspose.words.lic_2999.xml");
           // is =  Class.getResourceAsStream("com.aspose.words.lic_2999.xml");
            License asposeLicense = new License();
            asposeLicense.setLicense(is);
            System.out.println("Aspose isLicensed: " + asposeLicense.isLicensed());
            result = true;
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

    public static void doc2pdf(String inPath, String outPath) throws Exception {
        // 验证License 若不验证则转化出的pdf文档有水印
        if (!checkLicense()) {
            throw new Exception("com.aspose.words lic ERROR!");
        }

        System.out.println(inPath + " -> " + outPath);

        try {
            long old = System.currentTimeMillis();
            File file = new File(outPath);
            FileOutputStream os = new FileOutputStream(file);
            Document doc = new Document(inPath); // word文档
            // 支持RTF HTML,OpenDocument, PDF,EPUB, XPS转换
            doc.save(os, SaveFormat.PDF);
            long now = System.currentTimeMillis();
            System.out.println("convert OK! " + ((now - old) / 1000.0) + "秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void pdf2html(String inPath, String outPath) throws Exception {
        // 验证License 若不验证则转化出的pdf文档有水印
        if (!checkLicense()) {
            throw new Exception("com.aspose.words lic ERROR!");
        }

        System.out.println(inPath + " -> " + outPath);
     // For complete examples and data files, please go to https://github.com/aspose-pdf/Aspose.Pdf-for-Java
        try {
            long old = System.currentTimeMillis();
//            File file = new File(outPath);
//            FileOutputStream os = new FileOutputStream(file);
//            Document doc = new Document(inPath); // pdf文档
//            // 支持RTF HTML,OpenDocument, PDF,EPUB, XPS转换
//            doc.save(os, SaveFormat.HTML);
            
         Document pdfDocument  = new Document(inPath);
         // Instantiate HTML Save options object
         HtmlSaveOptions saveOptions = new HtmlSaveOptions();

         // Enable option to embed all resources inside the HTML
 
         // Save the output file
         pdfDocument.save(outPath, saveOptions);
            long now = System.currentTimeMillis();
            System.out.println("convert OK! " + ((now - old) / 1000.0) + "秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
