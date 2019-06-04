package xing.start.game;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RunWith(SpringRunner.class)
@EmbeddedKafka(count = 2,ports = {9092,9093})
@SpringBootTest(classes = GameApplicationTests.class)
public class GameApplicationTests {
	@Test
	public void contextLoads() throws Exception{
//		System.out.println(System.in.read());
		System.out.println("loading loaded");
	}
}
