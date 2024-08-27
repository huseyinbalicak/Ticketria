package org.ticketria.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.ticketria.dto.UserUpdate;
import org.ticketria.model.User;
import org.ticketria.service.UserService;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;
    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void getUserById() throws Exception {

        when(userService.getUser(1L)).thenReturn(prepareUser());

        mockMvc.perform(get("/api/v1/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("alex10@gmail.com"))
                .andExpect(jsonPath("$.username").value("alex10"));
    }

    @Test
    void getUserByEmail() throws Exception {

        String email = "alex10@gmail.com";
        User user = prepareUser();
        when(userService.findByEmail(email)).thenReturn(user);

        mockMvc.perform(get("/api/v1/users/email/{email}", email))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("alex10@gmail.com"))
                .andExpect(jsonPath("$.username").value("alex10"));
    }


    @Test
    void updateUser() throws Exception {

        UserUpdate userUpdate = new UserUpdate("newUsername");
        User updatedUser = prepareUser();
        updatedUser.setUsername("newUsername");
        when(userService.updateUser(1L, userUpdate)).thenReturn(updatedUser);

        mockMvc.perform(put("/api/v1/users/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"newUsername\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("alex10@gmail.com"))
                .andExpect(jsonPath("$.username").value("newUsername"));
    }

    @Test
    void deleteUser() throws Exception {

        doNothing().when(userService).deleteUser(1L);

        mockMvc.perform(delete("/api/v1/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("User is deleted"));
    }

    @Test
    void listUsers() throws Exception {

        User user1 = prepareUser();
        User user2 = new User();
        user2.setId(2L);
        user2.setEmail("hasan@gmail.com");
        user2.setUsername("hasan");
        List<User> users = List.of(user1, user2);
        when(userService.listUsers()).thenReturn(users);


        mockMvc.perform(get("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].email").value("alex10@gmail.com"))
                .andExpect(jsonPath("$[0].username").value("alex10"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].email").value("hasan@gmail.com"))
                .andExpect(jsonPath("$[1].username").value("hasan"));
    }



    private User prepareUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("alex10");
        user.setEmail("alex10@gmail.com");
        return user;
    }

}
