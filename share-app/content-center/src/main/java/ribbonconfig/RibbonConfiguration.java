package ribbonconfig;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wl_sun
 * @description TODO
 * @Data 2020/9/25
 */
@Configuration
public class RibbonConfiguration {
    @Bean
    public IRule ribbonRule(){
        //ctrl+alt+B：快速查看IRule的所有实现类
        return new RandomRule();
    }
}
