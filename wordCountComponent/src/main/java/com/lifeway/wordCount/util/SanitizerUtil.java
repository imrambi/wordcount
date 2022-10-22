package com.lifeway.wordCount.util;


import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class SanitizerUtil
{

	public static String sanitizeString(String value)
	{
		//Replace dashes with spaces.
		String replacedChars = value.replaceAll("[\\u2013-\\u204A]", " ").replaceAll("-", " ");
		//Escape out of new lines, etc, along with replace non-ascii characters
		return Jsoup.clean(StringEscapeUtils.escapeJson(replacedChars), Safelist.none());
	}

}
