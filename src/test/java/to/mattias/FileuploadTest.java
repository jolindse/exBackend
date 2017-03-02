package to.mattias;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import to.mattias.security.WebSecurityConfig;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * <h1>Created by Mattias on 2017-03-01.</h1>
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@TestPropertySource("classpath:application-test.properties")
@ContextConfiguration(classes = {ExBackendApplication.class})
@WebAppConfiguration
public class FileuploadTest {

    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;
    private UserDetails details;
    private ObjectMapper mapper;


    @Before
    public void setup() throws Exception {
        mapper = new ObjectMapper();
        details = new UserDetails("user1", "password");
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void testToLogin() throws Exception {
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(details)))
                .andDo(print());
    }

    @Test
    public void testToGetProjects() throws Exception {
        mockMvc.perform(get("/project")
                .header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImV4cCI6MTQ4ODQ1OTUyM30.n-PB6R5tZUKRj-9FWkjTv7gr7dKkCX5vNq9ZIDYAiRdRLbq-GSjQpj9M3PutDDSglpi0W6QjFHi6RtOyP6TJ6A"))
                .andDo(print());
    }


    class UserDetails {
        private String username, password;
        public UserDetails(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
