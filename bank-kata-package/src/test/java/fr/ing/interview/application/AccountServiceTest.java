package fr.ing.interview.application;

import fr.ing.interview.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class AccountServiceTest extends IntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AccountService accountService;

    @Test
    public void firstTest() {
        System.out.println(accountService.getAccount("1111").getBalance());
        assertThat(true);
    }

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