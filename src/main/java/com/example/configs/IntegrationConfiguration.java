package com.example.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

import java.io.File;

public class IntegrationConfiguration {

    @Bean(name = "messageChannelInput")
    public MessageChannel messageChannelInput() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel messageChannelFileWriter() {
        return new DirectChannel();
    }


    @Bean
    @Transformer(inputChannel = "messageChannelInput", outputChannel = "messageChannelFileWriter")
    public GenericTransformer<String, String> transformer() {
        return text -> {
           return text.toUpperCase();
        };
    }


    @Bean
    @ServiceActivator(inputChannel = "messageChannelFileWriter")
    public FileWritingMessageHandler myFileWriter() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("C:\\Users\\user\\IdeaProjects\\OnlineMarket_Demo\\src\\main\\resources"));
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        return handler;
    }
}
