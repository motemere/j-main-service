package org.jg.mainservice;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ApplicationTest {

    @Test
    @DisplayName("Application context loads successfully")
    void contextLoads() {
        // No need to implement this method
    }

    @Test
    @DisplayName("Main method test")
    void mainMethodTest() {
        try (var mockStaticSpringApplication = Mockito.mockStatic(SpringApplication.class)) {
            mockStaticSpringApplication
                    .when(() -> SpringApplication.run(Application.class, new String[]{}))
                    .thenReturn(null);

            Logger logger = (Logger) LoggerFactory.getLogger(Application.class);

            @SuppressWarnings("unchecked")
            Appender<ILoggingEvent> mockAppender = Mockito.mock(Appender.class);

            logger.addAppender(mockAppender);

            Application.main(new String[]{});

            mockStaticSpringApplication.verify(() -> SpringApplication.run(Application.class, new String[]{}));

            Mockito.verify(mockAppender).doAppend(Mockito.argThat((ILoggingEvent event) ->
                    event.getFormattedMessage().equals("Application started.")
            ));
        }
    }
}
