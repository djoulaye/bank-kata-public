package fr.ing.interview.application;

import fr.ing.interview.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class AccountServiceTest extends IntegrationTest {

    @Test
    public void getEndPointTest() throws Exception {
        mvc.perform(get("/account/get?accountNumber=1111")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.balance").value(120.0));
    }
}