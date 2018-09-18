package com.itheima.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * Convert<S,T>
 * 1.S:source需要转换的源类型
 * 2.T:target需要转换的目标类型
 */
public class DateConvert implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		//1.创建转换对象
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			//2.将传进来的数据进行转换
			return format.parse(source);
		} catch (ParseException e) {
			return null;
		}
	}

}
