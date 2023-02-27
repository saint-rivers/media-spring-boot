package com.saintrivers.mediaspringboot.config.kafka;

import java.util.HashMap;
import java.util.Map;

import com.saintrivers.mediaspringboot.feature.chat.ChatUseCase;
import com.saintrivers.mediaspringboot.feature.conversation.ConversationUseCase;
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

    // ================================ chat config =======================================
    @Bean
    ConcurrentKafkaListenerContainerFactory<String, ChatUseCase.ChatMessageRequest> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ChatUseCase.ChatMessageRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, ChatUseCase.ChatMessageRequest> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigurations(),
                new StringDeserializer(),
                new JsonDeserializer<>(ChatUseCase.ChatMessageRequest.class)
        );
    }

    // ================================ conversation config =======================================
    @Bean
    ConcurrentKafkaListenerContainerFactory<String, ConversationUseCase.ConversationResponse> kafkaConversationListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ConversationUseCase.ConversationResponse> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(conversationFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, ConversationUseCase.ConversationResponse> conversationFactory() {
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