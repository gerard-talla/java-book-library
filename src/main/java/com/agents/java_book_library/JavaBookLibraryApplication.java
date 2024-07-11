package com.agents.java_book_library;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication
public class JavaBookLibraryApplication {

	public static void main(String[] args) throws UnknownHostException {
		//SpringApplication.run(JavaBookLibraryApplication.class, args);
		SpringApplication app = new SpringApplication(JavaBookLibraryApplication.class);
		Environment env = app.run(args).getEnvironment();
		printAppInfo(env);
	}

	private static void printAppInfo(Environment env) throws UnknownHostException {
		String protocol = "http";
		String serverSslEnabled = env.getProperty("server.ssl.enabled");
		if (env.getProperty("server.ssl.key-store") != null
				&& serverSslEnabled != null
				&& serverSslEnabled.equals("true")) {
			protocol = "https";
		}

		//@formatter:off
		log.info("\n----------------------------------------------------------\n\t" +
						"Application '{}' is running! Access URLs:\n\t" +
						"Local: \t\t{}://localhost:{}{}\n\t" +
						"External: \t{}://{}:{}{}\n\t" +
						"Profile(s): \t{}" +
						"\n----------------------------------------------------------",
				env.getProperty("spring.application.name"),
				protocol,
				env.getProperty("server.port"),
				StringUtils.trimToEmpty(env.getProperty("server.servlet.context-path")),
				protocol,
				InetAddress.getLocalHost().getHostAddress(),
				env.getProperty("server.port"),
				StringUtils.trimToEmpty(env.getProperty("server.servlet.context-path")),
				env.getActiveProfiles());
		//@formatter:on
	}
}
