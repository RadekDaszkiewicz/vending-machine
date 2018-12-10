//package pl.edu.pjatk.tau.labone.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import pl.edu.pjatk.tau.labone.repository.ProductRepository;
//import pl.edu.pjatk.tau.labone.repository.ProductRepositoryImpl;
//import pl.edu.pjatk.tau.labone.service.DateService;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//
//@Configuration
//public class ProductConfiguration {
//    @Bean
//    public ProductRepository productRepository(
//            DataSource dataSource,
//            DateService dateService
//    ) throws SQLException {
//        return new ProductRepositoryImpl(dataSource, dateService);
//    }
//}