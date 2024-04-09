package javau9.securingjpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPublicData_ShouldReturnPublicData() throws Exception {
        mockMvc.perform(get("/public"))
                .andDo(print()) // This will print the request and response details
                .andExpect(status().isOk())
                .andExpect(content().string("This is public data"));
    }

    @Test
    @WithMockUser
    public void getPrivateData_WhenAuthenticated_ShouldReturnPrivateData() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(content().string("This is private data"));
    }

    @Test
    public void getPrivateData_WhenUnauthenticated_ShouldReturnUnauthorized() throws Exception {
        mockMvc.perform(get("/home"))
                .andExpect(status().isFound()) // Expect a redirect to the login page
                .andExpect(redirectedUrlPattern("**/login")); // Optionally check the redirect target
    }
}
