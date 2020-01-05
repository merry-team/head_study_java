package com.neteye.ai.characters;


/**
 * 包含两个实例，示例如何获取汉字的拼音等信息。
 * @author siqi
 *
 */
public class Sample {
 
	/**
	 * 字典使用实例
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			long time = System.currentTimeMillis();
 
			char ch = '打';
			//汉字单个字符
			System.out.println("====打字信息开始====");
			System.out.println("首字母："+Dic.GetFirstLetter(ch));
			System.out.println("拼音（中）："+Dic.GetPinyinCn(ch));
			System.out.println("拼音（英）："+Dic.GetPinyinEn(ch));
			System.out.println("部首："+Dic.GetBushou(ch));
			System.out.println("笔画数目："+Dic.GetBihua(ch));
			System.out.println("笔画："+Dic.GetBishun(ch));
			System.out.println("五笔："+Dic.GetWubi(ch));
			System.out.println("====打字信息结束====");
			
			//汉字字符串
			System.out.println("\r\n====汉字字符串====");
			System.out.println(Dic.GetPinyinEn("返回汉字字符串的拼音。"));
			System.out.println(Dic.GetPinyinCn("返回汉字字符串的拼音。"));
			System.out.println(Dic.GetFirstLetter("返回汉字字符串的拼音。"));
			System.out.println("====汉字字符串====\r\n");
			
			System.out.println("用时："+(System.currentTimeMillis()-time)+"毫秒");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
 
	}
}
