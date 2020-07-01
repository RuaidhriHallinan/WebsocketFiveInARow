package com.gen.fiveinarow;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS) //This cleans up and terminates the application after the test
public class WebsocketFiveInARowApplicationTest {

    @Test
    public void applicationStarts() {
        WebsocketFiveInARowApplication.main(new String[] {});
    }

}
