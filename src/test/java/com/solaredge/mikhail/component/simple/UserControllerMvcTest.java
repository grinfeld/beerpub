package com.solaredge.mikhail.component.simple;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class UserControllerMvcTest {

    @Autowired private MockMvc mockMvc;

    @MockBean private UserRepository userRepository;

    @Test
    void whenValidId_expectedReturnValidUser() throws Exception {
        setupUserControllerReturnValidUser();
        this.mockMvc.perform(get("/user/1")).andDo(print())
            .andExpect(status().isOk()).andExpect(content()
                .json("{" +
                    "\"username\":\"mikhail.g\"," +
                    "\"firstName\":\"Mikhail\"," +
                    "\"lastName\":\"Grinfeld\"" +
                "}"));
    }

    @Test
    void whenSomethingWrongInRepository_expectedRuntimeException() throws Exception {
        setupUserControllerException();
        this.mockMvc.perform(get("/user/0")).andDo(print())
            .andExpect(status().isBadRequest()).andExpect(content()
            .json("{" +
                "\"message\":\"Invalid id 0\"" +
            "}"));
    }

    private void setupUserControllerException() {
        Mockito.doThrow(new RuntimeException("Something went wrong"))
                .when(userRepository).getUserById(ArgumentMatchers.eq(1L));
    }

    private void setupUserControllerReturnValidUser() {
        Mockito.doReturn(new User("mikhail.g", "Mikhail", "Grinfeld"))
                .when(userRepository).getUserById(ArgumentMatchers.anyLong());
    }
}
