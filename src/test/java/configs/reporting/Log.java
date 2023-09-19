package configs.reporting;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.TestInfo;

import java.lang.reflect.Method;
import java.util.Optional;

public class Log {
    private static final Logger logger = LogManager.getLogger(Log.class);
    private static final String DEFAULT_CLASS_NAME = "CLASS_NAME_UNDEFINED";
    private static final String DEFAULT_METHOD_NAME = "METHOD_NAME_UNDEFINED";

    public static void info(String text) {
        logger.info(text);
    }

    public static void debug(String text) {
        logger.debug(text);
    }

    public static void error(String text) {
        logger.error(text);
        logger.traceExit();
    }

    public static void logTestStart(TestInfo testInfo) {
        String className = testInfo.getTestClass()
                .map(Class::getSimpleName)
                .orElse(DEFAULT_CLASS_NAME);
        Optional<Method> testMethod = testInfo.getTestMethod();
        testMethod.ifPresentOrElse(
                method -> {
                    if (method.getParameterCount() == 0) {
                        logger.info("Starting {}.{}:", className, method.getName());
                    } else {
                        logger.info("Starting {}.{}({}):", className, method.getName(), testInfo.getDisplayName());
                    }
                },
                () -> logger.info("Starting {}.{}({}):", className, DEFAULT_METHOD_NAME, testInfo.getDisplayName())
        );
    }
}
