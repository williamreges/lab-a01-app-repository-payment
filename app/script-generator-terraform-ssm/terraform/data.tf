
data "aws_ssm_parameter" "EUREKA_CLIENT_ENABLED" {
                   name="/config/app-repository-payment/eureka.client.enabled"
                   }
data "aws_ssm_parameter" "EUREKA_CLIENT_SERVICE_URL_DEFAULTZONE" {
                   name="/config/app-repository-payment/eureka.client.service-url.defaultZone"
                   }
data "aws_ssm_parameter" "LOGGING_PATTERN_CORRELATION" {
                   name="/config/app-repository-payment/logging.pattern.correlation"
                   }
data "aws_ssm_parameter" "LOGGING_INCLUDE_APPLICATION_NAME" {
                   name="/config/app-repository-payment/logging.include-application-name"
                   }
data "aws_ssm_parameter" "MANAGEMENT_TRACING_SAMPLING_PROBABILITY" {
                   name="/config/app-repository-payment/management.tracing.sampling.probability"
                   }
data "aws_ssm_parameter" "MANAGEMENT_ZIPKIN_TRACING_ENDPOINT" {
                   name="/config/app-repository-payment/management.zipkin.tracing.endpoint"
                   }
data "aws_ssm_parameter" "SERVER_PORT" {
                   name="/config/app-repository-payment/server.port"
                   }
data "aws_ssm_parameter" "SPRING_APPLICATION_NAME" {
                   name="/config/app-repository-payment/spring.application.name"
                   }
data "aws_ssm_parameter" "SPRING_CLOUD_CONFIG_ENABLED" {
                   name="/config/app-repository-payment/spring.cloud.config.enabled"
                   }
data "aws_ssm_parameter" "SPRING_JPA_SHOW_SQL" {
                   name="/config/app-repository-payment/spring.jpa.show-sql"
                   }
data "aws_ssm_parameter" "SPRING_JPA_HIBERNATE_DDL_AUTO" {
                   name="/config/app-repository-payment/spring.jpa.hibernate.ddl-auto"
                   }
data "aws_ssm_parameter" "SPRING_DATASOURCE_DRIVER_CLASS_NAME" {
                   name="/config/app-repository-payment/spring.datasource.driver-class-name"
                   }
data "aws_ssm_parameter" "SPRING_DATASOURCE_URL" {
                   name="/config/app-repository-payment/spring.datasource.url"
                   }
data "aws_ssm_parameter" "SPRING_DATASOURCE_USERNAME" {
                   name="/config/app-repository-payment/spring.datasource.username"
                   }
data "aws_ssm_parameter" "SPRING_DATASOURCE_PASSWORD" {
                   name="/config/app-repository-payment/spring.datasource.password"
                   }
data "aws_ssm_parameter" "SPRING_FLYWAY_DRIVER_CLASS_NAME" {
                   name="/config/app-repository-payment/spring.flyway.driver-class-name"
                   }
data "aws_ssm_parameter" "SPRING_FLYWAY_URL" {
                   name="/config/app-repository-payment/spring.flyway.url"
                   }
data "aws_ssm_parameter" "SPRING_FLYWAY_SCHEMAS" {
                   name="/config/app-repository-payment/spring.flyway.schemas"
                   }
data "aws_ssm_parameter" "SPRING_FLYWAY_USER" {
                   name="/config/app-repository-payment/spring.flyway.user"
                   }
data "aws_ssm_parameter" "SPRING_FLYWAY_PASSWORD" {
                   name="/config/app-repository-payment/spring.flyway.password"
                   }

