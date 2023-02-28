package com.saintrivers.mediaspringboot.config.kafka;

import java.util.HashMap;
import java.util.Map;

import com.saintrivers.mediaspringboot.feature.chat.ChatUseCase;
import com.saintrivers.mediaspringboot.feature.conversation.ConversationUseCase;
import com.saintrivers.mediaspringboot.feature.message.MessageUseCase;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;


@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaBroker;

    @Value("${custom.kafka.group}")
    private String kafkaGroup;
    // ================================ message config =======================================
    @Bean("kafkaMessageListenerContainerFactory")
    ConcurrentKafkaListenerContainerFactory<String, MessageUseCase.MessageRequest> kafkaMessageListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MessageUseCase.MessageRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(messageConsumerFactory());
        return factory;
    }

    @Bean("messageConsumerFactory")
    public ConsumerFactory<String, MessageUseCase.MessageRequest> messageConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigurations(),
                new StringDeserializer(),
                new JsonDeserializer<>(MessageUseCase.MessageRequest.class)
        );
    }
    // ================================ chat config =======================================
    @Bean("kafkaChatListenerContainerFactory")
    ConcurrentKafkaListenerContainerFactory<String, ChatUseCase.ChatMessageRequest> kafkaChatListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ChatUseCase.ChatMessageRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(chatConsumerFactory());
        return factory;
    }

    @Bean("chatConsumerFactory")
    public ConsumerFactory<String, ChatUseCase.ChatMessageRequest> chatConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigurations(),
                new StringDeserializer(),
                new JsonDeserializer<>(ChatUseCase.ChatMessageRequest.class)
        );
    }

    // ================================ conversation config =======================================
    @Bean("kafkaConversationListenerContainerFactory")
    ConcurrentKafkaListenerContainerFactory<String, ConversationUseCase.ConversationResponse> kafkaConversationListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ConversationUseCase.ConversationResponse> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(conversationConsumerFactory());
        return factory;
    }

    @Bean("conversationConsumerFactory")
    public ConsumerFactory<String, ConversationUseCase.ConversationResponse> conversationConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigurations(),
                new StringDeserializer(),
                new JsonDeserializer<>(ConversationUseCase.ConversationResponse.class)
        );
    }

    // ================================ consumer config =======================================
    @Bean
    public Map<String, Object> consumerConfigurations() {
        Map<String, Object> configurations = new HashMap<>();
        configurations.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBroker);
        configurations.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroup);
        configurations.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        configurations.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configurations.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return configurations;
    }
}