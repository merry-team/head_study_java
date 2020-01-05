package com.sharingcard.test;

import java.io.IOException;  

import com.itextpdf.text.pdf.PdfReader;  
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;  
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;  
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;  
  
public class PDFReader {  
  
 /** 
  * @param args 
  * @throws IOException 
  */  
 public static void main(String[] args) throws IOException {  
  System.out.print(getPdfFileText("E:\\User\\Neteye\\Neteye\\docment\\daily\\201905\\笔纸科技\\数学7年级下册.pdf"));  
 }  
  
 public static String getPdfFileText(String fileName) throws IOException {  
  PdfReader reader = new PdfReader(fileName);  
  PdfReaderContentParser parser = new PdfReaderContentParser(reader);  
  StringBuffer buff = new StringBuffer();  
  TextExtractionStrategy strategy;  
  for (int i = 1; i <= reader.getNumberOfPages(); i++) {  
   strategy = parser.processContent(i,  
     new SimpleTextExtractionStrategy());  
   buff.append(strategy.getResultantText());  
  }  
  return buff.toString();  
 }  
  
}