package to.mattias.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by mattias on 2017-03-20.
 */
@Configuration
@EnableWebMvc
public class AssetsConfig extends WebMvcConfigurerAdapter {

    private final String assetsBaseDir;

    @Autowired
    public AssetsConfig(@Value("${upload.base.dir}") String baseDir) {
        this.assetsBaseDir = baseDir;
    }

    /**
     * Adds the /assets/ endpoint and maps it to the directory
     * where uploaded files is located.
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/assets/**")
                .addResourceLocations("file:" + assetsBaseDir);
    }


}
