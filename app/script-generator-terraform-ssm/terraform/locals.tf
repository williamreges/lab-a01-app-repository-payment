locals {
    task_env_vars = [
{
          name: "EUREKA_CLIENT_ENABLED",
          value: data.aws_ssm_parameter.EUREKA_CLIENT_ENABLED.value
                  },
{
          name: "EUREKA_CLIENT_SERVICE_URL_DEFAULTZONE",
          value: data.aws_ssm_parameter.EUREKA_CLIENT_SERVICE_URL_DEFAULTZONE.value
                  },
{
          name: "LOGGING_PATTERN_CORRELATION",
          value: data.aws_ssm_parameter.LOGGING_PATTERN_CORRELATION.value
                  },
{
          name: "LOGGING_INCLUDE_APPLICATION_NAME",
          value: data.aws_ssm_parameter.LOGGING_INCLUDE_APPLICATION_NAME.value
                  },
{
          name: "MANAGEMENT_TRACING_SAMPLING_PROBABILITY",
          value: data.aws_ssm_parameter.MANAGEMENT_TRACING_SAMPLING_PROBABILITY.value
                  },
{
          name: "MANAGEMENT_ZIPKIN_TRACING_ENDPOINT",
          value: data.aws_ssm_parameter.MANAGEMENT_ZIPKIN_TRACING_ENDPOINT.value
                  },
{
          name: "SERVER_PORT",
          value: data.aws_ssm_parameter.SERVER_PORT.value
                  },
{
          name: "SPRING_APPLICATION_NAME",
          value: data.aws_ssm_parameter.SPRING_APPLICATION_NAME.value
                  },
{
          name: "SPRING_CLOUD_CONFIG_ENABLED",
          value: data.aws_ssm_parameter.SPRING_CLOUD_CONFIG_ENABLED.value
                  },
{
          name: "SPRING_JPA_SHOW_SQL",
          value: data.aws_ssm_parameter.SPRING_JPA_SHOW_SQL.value
                  },
{
          name: "SPRING_JPA_HIBERNATE_DDL_AUTO",
          value: data.aws_ssm_parameter.SPRING_JPA_HIBERNATE_DDL_AUTO.value
                  },
{
          name: "SPRING_DATASOURCE_DRIVER_CLASS_NAME",
          value: data.aws_ssm_parameter.SPRING_DATASOURCE_DRIVER_CLASS_NAME.value
                  },
{
          name: "SPRING_DATASOURCE_URL",
          value: data.aws_ssm_parameter.SPRING_DATASOURCE_URL.value
                  },
{
          name: "SPRING_DATASOURCE_USERNAME",
          value: data.aws_ssm_parameter.SPRING_DATASOURCE_USERNAME.value
                  },
{
          name: "SPRING_DATASOURCE_PASSWORD",
          value: data.aws_ssm_parameter.SPRING_DATASOURCE_PASSWORD.value
                  },
{
          name: "SPRING_FLYWAY_DRIVER_CLASS_NAME",
          value: data.aws_ssm_parameter.SPRING_FLYWAY_DRIVER_CLASS_NAME.value
                  },
{
          name: "SPRING_FLYWAY_URL",
          value: data.aws_ssm_parameter.SPRING_FLYWAY_URL.value
                  },
{
          name: "SPRING_FLYWAY_SCHEMAS",
          value: data.aws_ssm_parameter.SPRING_FLYWAY_SCHEMAS.value
                  },
{
          name: "SPRING_FLYWAY_USER",
          value: data.aws_ssm_parameter.SPRING_FLYWAY_USER.value
                  },
{
          name: "SPRING_FLYWAY_PASSWORD",
          value: data.aws_ssm_parameter.SPRING_FLYWAY_PASSWORD.value
                  }
] }
