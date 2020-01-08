package com.nbst.overall;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@ConfigurationProperties(prefix = "mail")
@Component
@Slf4j
public class MailConfiguration {
	private static String host;
	private static Integer port;
	private static String encoding;
	private static Boolean starttlsEnable;
	private static Boolean auth;
	private static String username;
	private static String password;
	private static Long timeout;
	private static String from;
	public static String getHost() {
		return host;
	}
	public static void setHost(String host) {
		MailConfiguration.host = host;
	}
	public static Integer getPort() {
		return port;
	}
	public static void setPort(Integer port) {
		MailConfiguration.port = port;
	}
	public static String getEncoding() {
		return encoding;
	}
	public static void setEncoding(String encoding) {
		MailConfiguration.encoding = encoding;
	}
	public static Boolean getStarttlsEnable() {
		return starttlsEnable;
	}
	public static void setStarttlsEnable(Boolean starttlsEnable) {
		MailConfiguration.starttlsEnable = starttlsEnable;
	}
	public static Boolean getAuth() {
		return auth;
	}
	public static void setAuth(Boolean auth) {
		MailConfiguration.auth = auth;
	}
	public static String getUsername() {
		return username;
	}
	public static void setUsername(String username) {
		MailConfiguration.username = username;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		MailConfiguration.password = password;
	}
	public static Long getTimeout() {
		return timeout;
	}
	public static void setTimeout(Long timeout) {
		MailConfiguration.timeout = timeout;
	}
	public static String getFrom() {
		return from;
	}
	public static void setFrom(String from) {
		MailConfiguration.from = from;
	}
	
}
