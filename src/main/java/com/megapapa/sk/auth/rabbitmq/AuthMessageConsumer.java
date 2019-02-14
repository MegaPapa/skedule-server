package com.megapapa.sk.auth.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.megapapa.sk.auth.entity.AuthResponseMessage;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public class AuthMessageConsumer extends DefaultConsumer {

    private static final Logger logger = LoggerFactory.getLogger(AuthMessageConsumer.class);
    private Map<UUID, AuthResponseMessage> brokerResponses;

    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel the channel to which this consumer is attached
     */
    public AuthMessageConsumer(Channel channel, Map<UUID, AuthResponseMessage> brokerResponses) {
        super(channel);
        this.brokerResponses = brokerResponses;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        AuthResponseMessage responseMessage = getResponseMessage(body);
        if (responseMessage != null) {

        }
    }

    private AuthResponseMessage getResponseMessage(byte[] messageBytes) {
        ObjectMapper mapper = new ObjectMapper();
        AuthResponseMessage responseMessage = null;
        try {
            responseMessage = mapper.readValue(messageBytes, AuthResponseMessage.class);
        } catch (IOException e) {
            logger.error("Can't map AuthReponseMessage!", e);
        }
        return responseMessage;
    }
}
