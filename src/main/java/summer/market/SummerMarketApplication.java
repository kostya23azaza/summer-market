package summer.market;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;

@SpringBootApplication
public class SummerMarketApplication {

	public static void main(String[] args) {
		//SpringApplication.run(SummerMarketApplication.class, args);
		List<String> list = SpringFactoriesLoader.loadFactoryNames(EnableAutoConfiguration.class, null);
		System.out.println(list.size());
		list.forEach(System.out::println);
	}
}
