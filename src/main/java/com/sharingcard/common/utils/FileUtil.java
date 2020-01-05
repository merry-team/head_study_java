package com.sharingcard.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.sharingcard.card.domain.AccountInfo;


public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
		File targetFile = new File(filePath);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		FileOutputStream out = new FileOutputStream(filePath + fileName);
		out.write(file);
		out.flush();
		out.close();
	}

	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static String renameToUUID(String fileName) {
		return UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
	}
	
	/**
	 * 获取指定路径下的所有文件列表
	 *
	 * @param dir 要查找的目录
	 * @return
	 */
	public static List<String> getFileList(String dir,String filepre) {
	    List<String> listFile = new ArrayList<>();
	    File dirFile = new File(dir);
	    //如果不是目录文件，则直接返回
	    if (dirFile.isDirectory()) {
	        //获得文件夹下的文件列表，然后根据文件类型分别处理
	        File[] files = dirFile.listFiles();
	        if (null != files && files.length > 0) {
	            //根据时间排序
	            Arrays.sort(files, new Comparator<File>() {
	                public int compare(File f1, File f2) {
	                    return (int) (f1.lastModified() - f2.lastModified());
	                }
	 
	                public boolean equals(Object obj) {
	                    return true;
	                }
	            });
	            for (File file : files) {
	                //如果不是目录，直接添加
	                if (!file.isDirectory() && file.getName().startsWith(filepre)) {
	                    listFile.add(file.getAbsolutePath());
	                } else {
	                    //对于目录文件，递归调用
	                    listFile.addAll(getFileList(file.getAbsolutePath(),filepre));
	                }
	            }
	        }
	    }
	    return listFile;
	}
	
	public static void moveTotherFolders(String pathName,String fileName,String ansPath){
	    String startPath = pathName + File.separator + fileName;
	    String endPath = ansPath + File.separator ;
	    try {
	        File startFile = new File(startPath);
	        File tmpFile = new File(endPath);//获取文件夹路径
	        if(!tmpFile.exists()){//判断文件夹是否创建，没有创建则创建新文件夹
	            tmpFile.mkdirs();
	        }
	        //System.out.println(endPath + startFile.getName());
	        if (startFile.renameTo(new File(endPath + startFile.getName()))) {
	            System.out.println("File is moved successful!");
	            logger.info("文件移动成功！文件名：《{}》 目标路径：{}",fileName,endPath);
	        } else {
	            System.out.println("File is failed to move!");
	            logger.info("文件移动失败！文件名：《{}》 起始路径：{}",fileName,startPath);
	        }
	    } catch (Exception e) {
	    	logger.error("文件移动异常！文件名：《{}》 起始路径：{}",fileName,startPath);

	    }
	}
	
	  /** 
     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。 
     */  
    public static void readFileByBytes(String fileName) {  
        File file = new File(fileName);  
        InputStream in = null;  
        try {  
            System.out.println("以字节为单位读取文件内容，一次读一个字节：");  
            // 一次读一个字节  
            in = new FileInputStream(file);  
            int tempbyte;  
            while ((tempbyte = in.read()) != -1) {  
                System.out.write(tempbyte);  
            }  
            in.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
            return;  
        }  
        try {  
            System.out.println("以字节为单位读取文件内容，一次读多个字节：");  
            // 一次读多个字节  
            byte[] tempbytes = new byte[100];  
            int byteread = 0;  
            in = new FileInputStream(fileName);  
            showAvailableBytes(in);  
            // 读入多个字节到字节数组中，byteread为一次读入的字节数  
            while ((byteread = in.read(tempbytes)) != -1) {  
                System.out.write(tempbytes, 0, byteread);  
            }  
        } catch (Exception e1) {  
            e1.printStackTrace();  
        } finally {  
            if (in != null) {  
                try {  
                    in.close();  
                } catch (IOException e1) {  
                }  
            }  
        }  
    }  
  
    /** 
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件 
     */  
    public static void readFileByChars(String fileName) {  
        File file = new File(fileName);  
        Reader reader = null;  
        try {  
            System.out.println("以字符为单位读取文件内容，一次读一个字节：");  
            // 一次读一个字符  
            reader = new InputStreamReader(new FileInputStream(file));  
            int tempchar;  
            while ((tempchar = reader.read()) != -1) {  
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。  
                // 但如果这两个字符分开显示时，会换两次行。  
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。  
                if (((char) tempchar) != '\r') {  
                    System.out.print((char) tempchar);  
                }  
            }  
            reader.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        try {  
            System.out.println("以字符为单位读取文件内容，一次读多个字节：");  
            // 一次读多个字符  
            char[] tempchars = new char[30];  
            int charread = 0;  
            reader = new InputStreamReader(new FileInputStream(fileName));  
            // 读入多个字符到字符数组中，charread为一次读取字符数  
            while ((charread = reader.read(tempchars)) != -1) {  
                // 同样屏蔽掉\r不显示  
                if ((charread == tempchars.length)  
                        && (tempchars[tempchars.length - 1] != '\r')) {  
                    System.out.print(tempchars);  
                } else {  
                    for (int i = 0; i < charread; i++) {  
                        if (tempchars[i] == '\r') {  
                            continue;  
                        } else {  
                            System.out.print(tempchars[i]);  
                        }  
                    }  
                }  
            }  
  
        } catch (Exception e1) {  
            e1.printStackTrace();  
        } finally {  
            if (reader != null) {  
                try {  
                    reader.close();  
                } catch (IOException e1) {  
                }  
            }  
        }  
    }  
  
    /** 
     * 以行为单位读取文件，常用于读面向行的格式化文件 
     */  
//    public static List<AccountInfo> readFileByLines(String fileName) {  
//        File file = new File(fileName); 
//        List<AccountInfo> listAccountInfo =  new ArrayList();
//        BufferedReader reader = null;  
//        try {  
//            System.out.println("以行为单位读取文件内容，一次读一整行：");  
//            reader = new BufferedReader(new FileReader(file));  
//            String tempString = null;  
//            int line = 1;  
//            // 一次读入一行，直到读入null为文件结束  
//            while ((tempString = reader.readLine()) != null) {  
//                // 显示行号  
//                System.out.println("line " + line + ": " + tempString);  
//                line++;  
//                String[] cdrdetails = tempString.split(",");
//                AccountInfo accountInfo = new AccountInfo();
//                //accountInfo.setCurrentDate(cdrdetails[6]);
//                accountInfo.setCurrentDate(cdrdetails[20]);
//                accountInfo.setMobile(cdrdetails[2]);
//                accountInfo.setReceiveMobile(cdrdetails[4]);
//                listAccountInfo.add(accountInfo);                
//            }  
//            reader.close();  
//            return listAccountInfo;
//        } catch (IOException e) {  
//            e.printStackTrace();  
//            return null;
//        } finally {  
//            if (reader != null) {  
//                try {  
//                    reader.close();  
//                } catch (IOException e1) {  
//                }  
//            }  
//        }  
//    }  
//  
    /** 
     * 随机读取文件内容 
     */  
    public static void readFileByRandomAccess(String fileName) {  
        RandomAccessFile randomFile = null;  
        try {  
            System.out.println("随机读取一段文件内容：");  
            // 打开一个随机访问文件流，按只读方式  
            randomFile = new RandomAccessFile(fileName, "r");  
            // 文件长度，字节数  
            long fileLength = randomFile.length();  
            // 读文件的起始位置  
            int beginIndex = (fileLength > 4) ? 4 : 0;  
            // 将读文件的开始位置移到beginIndex位置。  
            randomFile.seek(beginIndex);  
            byte[] bytes = new byte[10];  
            int byteread = 0;  
            // 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。  
            // 将一次读取的字节数赋给byteread  
            while ((byteread = randomFile.read(bytes)) != -1) {  
                System.out.write(bytes, 0, byteread);  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (randomFile != null) {  
                try {  
                    randomFile.close();  
                } catch (IOException e1) {  
                }  
            }  
        }  
    }  
  
    /** 
     * 显示输入流中还剩的字节数 
     */  
    private static void showAvailableBytes(InputStream in) {  
        try {  
            System.out.println("当前字节输入流中的字节数为:" + in.available());  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    } 
    
    /** 
     * 显示输入流中还剩的字节数 
     */  
    public static String getfileName (String pathFileName ) {  
    	 String fName = pathFileName.trim();      	  
         return fName.substring(fName.lastIndexOf(File.separator)+1); 
    }  
}
