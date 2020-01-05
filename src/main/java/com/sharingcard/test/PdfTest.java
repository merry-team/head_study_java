package com.sharingcard.test;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.UUID;
 
import javax.imageio.ImageIO;
 
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Test;
 
public class PdfTest {
 
	@Test
	public void testPdf() {
		String path = "E:\\User\\Neteye\\Neteye\\docment\\daily\\201905\\笔纸科技\\数学7年级下册.pdf";
		File file = new File(path);
		InputStream is = null;
		PDDocument document = null;
		try {
			if (path.endsWith(".pdf")) {
				document = PDDocument.load(file);
				int pageSize = document.getNumberOfPages();
				// 一页一页读取
				for (int i = 0; i < pageSize; i++) {
					// 文本内容
					PDFTextStripper stripper = new PDFTextStripper();
					// 设置按顺序输出
					stripper.setSortByPosition(true);
					stripper.setStartPage(i + 1);
					stripper.setEndPage(i + 1);
					String text = stripper.getText(document);
					
					//System.out.println(text.trim());
	                String lines[] = text.split("\\r?\\n");
	                for (String line : lines) {
	                    System.out.println(line);
	                }
					System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-");
 
					// 图片内容
					PDPage page = document.getPage(i);
					PDResources resources = page.getResources();
					Iterable<COSName> cosNames = resources.getXObjectNames();
					if (cosNames != null) {
						Iterator<COSName> cosNamesIter = cosNames.iterator();
						while (cosNamesIter.hasNext()) {
							COSName cosName = cosNamesIter.next();
							if (resources.isImageXObject(cosName)) {
								PDImageXObject Ipdmage = (PDImageXObject) resources.getXObject(cosName);
								BufferedImage image = Ipdmage.getImage();
								FileOutputStream out = new FileOutputStream("D:\\temp\\temp\\" + UUID.randomUUID() + ".png");
								try {
									ImageIO.write(image, "png", out);
								} catch (IOException e) {
								} finally {
									try {
										out.close();
									} catch (IOException e) {
									}
								}
							}
						}
					}
				}
			}
		} catch (InvalidPasswordException e) {
		} catch (IOException e) {
		} finally {
			try {
				if (document != null) {
					document.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
			}
		}
	}
	
}
