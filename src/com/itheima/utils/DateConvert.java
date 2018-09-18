package com.itheima.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * Convert<S,T>
 * 1.S:source��Ҫת����Դ����
 * 2.T:target��Ҫת����Ŀ������
 */
public class DateConvert implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		//1.����ת������
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			//2.�������������ݽ���ת��
			return format.parse(source);
		} catch (ParseException e) {
			return null;
		}
	}

}
