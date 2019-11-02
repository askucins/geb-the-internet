//appender("STDOUT", ConsoleAppender) {
//    encoder(PatternLayoutEncoder) {
//        pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
//    }
//}
//appender("FILE", RollingFileAppender) {
//    rollingPolicy(TimeBasedRollingPolicy) {
//        fileNamePattern = "logs/test.%d{yyyy-MM-dd}.log"
//        maxHistory = 999
//    }
//    encoder(PatternLayoutEncoder) {
//        pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
//    }
//}
//logger("org.askucins", DEBUG, ["FILE", "STDOUT"])
//
//root(DEBUG, ["FILE", "STDOUT"])
