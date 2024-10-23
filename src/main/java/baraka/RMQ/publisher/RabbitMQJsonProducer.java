package baraka.RMQ.publisher;

import baraka.RMQ.dto.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


    @Service
    public class RabbitMQJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(Employee employee) {
            LOGGER.info("Json Message sent -> {}", employee.toString());
            try {
                rabbitTemplate.convertAndSend(exchange, routingJsonKey, employee);
            } catch (Exception e) {
                LOGGER.error("Error sending message to RabbitMQ", e);
                // Handle the exception (e.g., retry, log for further investigation)
            }
        }
}
