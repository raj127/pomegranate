package com.darkmi.common.tools;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class Cn2Spell {
	/** 
	* 汉字转换位汉语拼音首字母，英文字符不变 
	* @param chines 汉字 
	* @return 拼音 
	*/
	public static String converterToFirstSpell(String chines) {
		String pinyinName = "";
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					if (PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat) != null
							&& PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat).length > 0) {
						pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0].charAt(0);
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName += nameChar[i];
			}
		}
		return pinyinName;
	}

	/** 
	* 汉字转换位汉语拼音，英文字符不变 
	* @param chines 汉字 
	* @return 拼音 
	*/
	public static String converterToSpell(String chines) {
		String pinyinName = "";
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					if (PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat) != null
							&& PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat).length > 0) {
						pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0];
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName += nameChar[i];
			}
		}
		return pinyinName;
	}

	
	/** 
	* 汉字转换位汉语拼音，英文字符不变 
	* @param chines 汉字 
	* @return 拼音 
	*/
	public static String converterToSpell(String chines, boolean firstCharUpcase) {
		String pinyinName = "";
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					if (PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat) != null
							&& PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat).length > 0) {
						pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0];
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName += nameChar[i];
			}
		}
		return pinyinName;
	}

	public static void main(String[] args) {
		System.out.println(converterToFirstSpell("测试（测试测试测试）"));
		System.out.println(converterToSpell("测试（测试测试测试）"));
		System.out.println(converterToSpell("测试（测试测试测试）",true));
	}
}
