package com.cnn.testjavers.config;

import org.javers.spring.auditable.AuthorProvider;
import org.javers.spring.auditable.MockAuthorProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**∂
 * @author ningning.cheng
 * @date 2021/10/23
 **/
@Configuration
public class JaverConfig {

    @Bean
    public AuthorProvider authProvider(){
      return new MyAuthorProvider();
    }

    public static class  MyAuthorProvider extends MockAuthorProvider{
        @Override
        public String provide() {
            return "ningning";
        }
    }
}
