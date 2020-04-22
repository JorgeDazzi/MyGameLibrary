package br.dazzi.gamelibrary.controller;

import br.dazzi.gamelibrary.GamelibraryApplication;
import br.dazzi.gamelibrary.controller.response.LibraryResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("Test-LIBRARY")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = GamelibraryApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LibraryControllerTest {

    private final static String URI = "/v1/library";

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;


    @BeforeAll
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    @Order(1)
    void findAllEmpty() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get(URI+"/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").exists());

//        System.out.println("TEST :: Find all - Empty result");
    }

    @Test
    @Order(2)
    void findById_ERROR() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get(URI+"/{id}",89))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error_list").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error_list[0].code").value(404));

//        System.out.println("TEST :: Find By ID - ERROR");
    }

    @Test
    @Order(3)
    void updateNonExistentItem() throws Exception{
        LibraryResponse game = new LibraryResponse();
        game.setId((long) 1);
        game.setName("Dota 2");
        game.setDesc("pew pew pew");
        game.setFree(true);
        game.setWebsite("www.dota2.com/blog");
        game.setPublishers("Valve Inc");
        game.setDev("Valve Inc");
        game.setRequired_age(0);
        game.setSteamAppId((long) 570);
        game.setPlatforms(new HashMap<String, Boolean>(){{
            put("windows", false);
            put("linux", true);
            put("mac", false);
        }});

        this.mockMvc.perform(MockMvcRequestBuilders.put(URI + "/").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(asJsonString(game)))
                .andDo(print())
                .andExpect(status().isNotFound());

        this.mockMvc.perform(MockMvcRequestBuilders.get(URI+"/{id}",1))
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    @Test
    @Order(4)
    void addGame() throws Exception{
        LibraryResponse game = new LibraryResponse();
        game.setName("Dota 2");
        game.setDesc("pew pew pew");
        game.setFree(true);
        game.setPublishers("Valve ©");
        game.setDev("Valve ©");
        game.setRequired_age(0);
        game.setSteamAppId((long) 570);
        game.setPlatforms(new HashMap<String, Boolean>(){{
            put("windows", true);
            put("linux", false);
            put("mac", true);
        }});

        this.mockMvc.perform(MockMvcRequestBuilders.post(URI+"/").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(asJsonString(game)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(game.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.platforms.windows").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.platforms.linux").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.platforms.mac").value(true));

//        System.out.println("TEST :: Add Game");
    }

    @Test
    @Order(5)
    void update() throws Exception{
        LibraryResponse game = new LibraryResponse();
        game.setId((long) 1);
        game.setName("Dota 2");
        game.setDesc("pew pew pew");
        game.setFree(true);
        game.setWebsite("www.dota2.com/blog");
        game.setPublishers("Valve Inc");
        game.setDev("Valve Inc");
        game.setRequired_age(0);
        game.setSteamAppId((long) 570);
        game.setPlatforms(new HashMap<String, Boolean>(){{
            put("windows", false);
            put("linux", true);
            put("mac", false);
        }});

        this.mockMvc.perform(MockMvcRequestBuilders.put(URI + "/").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(asJsonString(game)))
                .andDo(print())
                .andExpect(status().isCreated());

        this.mockMvc.perform(MockMvcRequestBuilders.get(URI+"/{id}",1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(game.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.platforms.windows").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.platforms.linux").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.platforms.mac").value(false));

    }

    @Test
    @Order(6)
    void removeNonExistentItem() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete(URI + "/{id}", 89))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(7)
    void remove() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete(URI + "/{id}", 1))
                .andDo(print())
                .andExpect(status().isNoContent());

        this.mockMvc.perform(MockMvcRequestBuilders.get(URI+"/{id}",1))
                .andDo(print())
                .andExpect(status().isNotFound());
    }



    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}