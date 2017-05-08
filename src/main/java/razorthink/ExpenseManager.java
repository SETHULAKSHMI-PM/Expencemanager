package razorthink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Created by sethulakshmi on 24/4/17.
 */

@EnableSwagger2
@SpringBootApplication
public class ExpenseManager
{
    public static void main(String[] args)
    {
        SpringApplication.run(ExpenseManager.class, args);
    }
    @Bean
    public Docket
    newsApi()
    {
        return new Docket(DocumentationType.SWAGGER_2).groupName("Expense").apiInfo(apiInfo()).select().paths(regex("/rest.*")).build();
    }
    private ApiInfo apiInfo()
    {
        return new ApiInfoBuilder().title("REST Documentation").version("2.0").build();
    }
}
