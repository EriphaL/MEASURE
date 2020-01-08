package com.nbst.comnutil;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Tools {

	private static final byte[] DES_KEY = { 27, 11, -100, 88, -34, -89, -128,
			-61 };

	private static final FieldPosition HELPER_POSITION = new FieldPosition(0);
	
	private final static NumberFormat numberFormat = new DecimalFormat("0000");
	 
    private static int seq = 0;
 
    private static final int MAX = 9999;
	
	// 利用Introspector和PropertyDescriptor 将Bean --> Map
	public static Map<String, Object> transBean2Map(Object obj) {

		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					if (value != null) {
						if (value.equals("")) {
							value = null;
						}
					}
					map.put(key, value);
				}

			}
		} catch (Exception e) {
			// LOG.debug("transBean2Map Error " + e);
			log.error("transBean2Map Error " + e);
		}
		return map;
	}

	/**
	 * 
	 * @Title: careatID
	 * @Description: 创建主键ID (46位)
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String careatId() {
		String ID = UUID.randomUUID().toString();
		ID = ID.replace("-", "");
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");
		ID = formatter.format(date) + ID;
		return ID;
	}
	
	/**
	 * 创建主键ID(32位)
	 * 
	 * @return 32位带数字的字符串
	 * 
	 * @throws
	 */
	public static String careatId32() {
		String ID = UUID.randomUUID().toString();
		ID = ID.replace("-", "");
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");
		ID = formatter.format(date) + ID;
		ID = ID.substring(0, 32);
		return ID;
	}
	
	/**
	 * 创建主键ID(25位)
	 * 
	 * @return 25位带数字的字符串
	 * 
	 * @throws
	 */
	public static String careatId25() {
		String ID = UUID.randomUUID().toString();
		ID = ID.replace("-", "");
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");
		ID = formatter.format(date) + ID;
		ID = ID.substring(0, 25);
		return ID;
	}
	
	public static synchronized String generateSequenceNo(String prefix) {
		Calendar rightNow = Calendar.getInstance();
        StringBuffer sb = new StringBuffer();
        sb.append(prefix);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddhhmmss");
        formatter.format(rightNow.getTime(), sb, HELPER_POSITION);
        numberFormat.format(seq, sb, HELPER_POSITION);
        if (seq == MAX) {
            seq = 0;
        } else {
            seq++;
        }
        log.info("Generate code ,the code is :" + sb.toString());
        return sb.toString();
    }

	/**
	 * 数据加密，算法（DES）
	 * 
	 * @param data
	 *            要进行加密的数据
	 * @return 加密后的数据
	 */
	public static String encrypt(String data) {
		String encryptedData = null;
		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = new SecureRandom();
			DESKeySpec deskey = new DESKeySpec(DES_KEY);
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(deskey);
			// 加密对象
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key, sr);
			// 加密，并把字节数组编码成字符串
			encryptedData = new sun.misc.BASE64Encoder().encode(cipher
					.doFinal(data.getBytes()));
		} catch (Exception e) {
			log.error("加密错误，错误信息：", e);
		}
		return encryptedData;
	}

	/**
	 * 数据解密，算法（DES）
	 * 
	 * @param cryptData
	 *            加密数据
	 * 
	 * @return 解密后的数据
	 */
	public static String decrypt(String cryptData) {
		String decryptedData = null;
		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = new SecureRandom();
			DESKeySpec deskey = new DESKeySpec(DES_KEY);
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(deskey);
			// 解密对象
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key, sr);
			// 把字符串解码为字节数组，并解密
			decryptedData = new String(
					cipher.doFinal(new sun.misc.BASE64Decoder()
							.decodeBuffer(cryptData)));
		} catch (Exception e) {
			log.error("解密错误，错误信息：", e);
		}
		return decryptedData;
	}

	/**
	 * 
	 * @Title: getNormalResult
	 * @Description: 根据Sql语句返回值返回结果集
	 * @param @param num Sql语句返回值
	 * @param @param code 哪种类型sql语句
	 * @param @param e 错误类型
	 * @param @return
	 * @return NormalResult
	 * @throws
	 */
	public static NormalResult getNormalResult(Integer num, String code,
			Object e) {
		NormalResult result = new NormalResult();
		if (num == 1) {
			result.setCode("0000");
			if ("insert".equals(code)) {
				result.setMessage("新增成功");
			} else if ("update".equals(code)) {
				result.setMessage("修改成功");
			} else if ("delete".equals(code)) {
				result.setMessage("删除成功");
			}
		} else {
			result.setCode("9999");
			result.setDataset(e);
			if ("insert".equals(code)) {
				result.setMessage("新增失败");
			} else if ("update".equals(code)) {
				result.setMessage("修改失败");
			} else if ("delete".equals(code)) {
				result.setMessage("删除失败");
			}
		}
		return result;
	}

}
