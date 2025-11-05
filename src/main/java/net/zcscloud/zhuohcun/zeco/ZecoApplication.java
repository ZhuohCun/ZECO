package net.zcscloud.zhuohcun.zeco;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
// Takukou Sun (aka. Zhuohong Cun) © All Rights Reserved
@EnableJpaAuditing
@SpringBootApplication
public class ZecoApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(ZecoApplication.class, args);
    }

}
