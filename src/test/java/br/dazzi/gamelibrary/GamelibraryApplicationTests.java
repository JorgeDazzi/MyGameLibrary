package br.dazzi.gamelibrary;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("test")
@SpringBootTest(
		classes = GamelibraryApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class GamelibraryApplicationTests {


}
