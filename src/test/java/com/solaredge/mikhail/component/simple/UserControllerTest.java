package com.solaredge.mikhail.component.simple;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UserControllerTest {

    @MockBean
    private UserRepository userRepository;

    @Test
    void whenValidId_expectedReturnValidUser() {
        UserController userController = setupUserControllerReturnValidUser();
        assertThat(userController.getUser(1)).isNotNull()
            .hasFieldOrPropertyWithValue("username", "mikhail.g")
            .hasFieldOrPropertyWithValue("firstName", "Mikhail")
            .hasFieldOrPropertyWithValue("lastName", "Grinfeld");
    }

    @Test
    void whenSomethingWrongInRepository_expectedRuntimeException() {
        UserController userController = setupUserControllerException();
        assertThrows(
                RuntimeException.class,
                () -> userController.getUser(1)
        );
    }

    private UserController setupUserControllerException() {
        Mockito.doThrow(new RuntimeException("Something went wrong"))
            .when(userRepository).getUserById(ArgumentMatchers.eq(1L));
        return new UserController(userRepository);
    }

    private UserController setupUserControllerReturnValidUser() {
        Mockito.doReturn(new User("mikhail.g", "Mikhail", "Grinfeld"))
            .when(userRepository).getUserById(ArgumentMatchers.anyLong());
        return new UserController(userRepository);
    }
}