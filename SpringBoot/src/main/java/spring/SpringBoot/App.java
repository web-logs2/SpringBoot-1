package spring.SpringBoot;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, MybatisAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
@EnableScheduling
//@MapperScan(value = {"spring.SpringBoot.mapper"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
