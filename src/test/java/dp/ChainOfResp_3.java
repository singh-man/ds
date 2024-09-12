package dp;

import org.junit.jupiter.api.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ChainOfResp_3 {

    @FunctionalInterface
    interface LogHandler {
        void handle(Level level, String message);

        default LogHandler andThen(LogHandler nextHandler) {
            return (level, message) -> {
                this.handle(level, message);
                nextHandler.handle(level, message);
            };
        }
    }

    private LogHandler logChain;

    public void log(Level level, String message) {
        logChain.handle(level, message);
    }

    @Test
    public void test() {
        // Chain of Responsibility setup using lambdas
        LogHandler infoHandler = (level, message) -> {
            if (level == Level.INFO) {
                System.out.println("INFO: " + message);
            }
        };

        LogHandler debugHandler = (level, message) -> {
            if (level == Level.FINE) {
                System.out.println("FINE/DEBUG: " + message);
            }
        };

        LogHandler errorHandler = (level, message) -> {
            if (level == Level.SEVERE) {
                System.out.println("SEVERE/ERROR: " + message);
            }
        };

        // Create the chain
        logChain = infoHandler
                .andThen(debugHandler)
                .andThen(errorHandler);

        Logger logger = Logger.getLogger(this.getClass().getName());
        // Let's test different scenarios:
        logger.log(Level.INFO, "This is an informational message.");
        logger.log(Level.FINE, "This is a fine/ebug message, something minor.");
        logger.log(Level.SEVERE, "This is an severe/error message, something failed.");
    }
}

