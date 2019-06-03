package xing.start.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class GameApplication {
	private static String last_msg;
	private final Logger logger = LoggerFactory.getLogger(GameApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(GameApplication.class, args);

	}

	@Bean
	public Logger getLogger() {
		Logger logger = LoggerFactory.getLogger("game应用");
		return LoggerFactory.getLogger("格木应用");
	}
	@Autowired
	private KafkaTemplate<Object,Object> template;

	@GetMapping("/send/{input}")
	public String sendFoo(@PathVariable String input) {
		this.template.send("topic_input", input);
		return last_msg;
	}
	// kafka test: https://my.oschina.net/keking/blog/3056698
	@KafkaListener(id = "webGroup", topics = "topic_input")
	public void listen(String input) {
		logger.info("input value: {}" , input);
		last_msg = input;
	}

}
