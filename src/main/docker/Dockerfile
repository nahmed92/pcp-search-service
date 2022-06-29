#ARG DELTA_DOCKER_REGISTRY=dockerdev.deltads.ent:7070
#ARG BASE_IMAGE_NAME=delta
#ARG BASE_IMAGE_TAG=jdk11-jre-alpine-appd-contrast

FROM openjdk:11-jre

MAINTAINER Delta Dental Group Member Online - DCM
VOLUME /tmp
ADD pcp-search-service-1.0.0.jar pcp-search-service-1.0.0.jar
ADD contrast.jar contrast.jar
RUN sh -c 'touch /pcp-search-service-1.0.0.jar'
RUN sh -c 'touch /contrast.jar'
ENV JAVA_OPTS=""
ENTRYPOINT java $JAVA_OPTS \
	-javaagent:$APPD_ROOT/javaagent.jar \
        -javaagent:/contrast.jar \
	-Dcontrast.dir=/opt/docker/logs/contrast \
	-Dcontrast.override.appname=${SERVICE_NAME} \
	-Dcontrast.enabled=${CONTRAST_ENABLED} \
	-Dcontrast.env=${CONTRAST_ENV} \
	-Dcontrast.server.name=${CONTRAST_SERVER_NAME} \
	-Dappdynamics.controller.hostName=${APPD_CONTROLLER_HOSTNAME} \
	-Dappdynamics.controller.port=${APPD_PORT} \
	-Dappdynamics.agent.runtime.dir=${APPD_LOG_LOCATION} \
	-Dappdynamics.agent.applicationName=${APPD_APPLICATION_NAME} \
	-Dappdynamics.agent.nodeName=${APPD_NODE_NAME} \
	-Dappdynamics.agent.tierName=${APPD_TIER_NAME} \
	-Dappdynamics.agent.accountName=${APPD_ACCOUNT_NAME} \
	-Dappdynamics.agent.accountAccessKey=${APPD_ACCESS_KEY} \
	-Dappdynamics.agent.runtime.dir=${APPD_LOG_LOCATION} \
	-Dappdynamics.agent.uniqueHostId=${HOST_NAME} \
	-Dappdynamics.controller.ssl.enabled=${APPD_SSL_ENABLED} \
	-DplatformLogLevel=${PLATFORM_LOG_LEVEL} \
	-DsystemLogLevel=${SYSTEM_LOG_LEVEL} \
	-Dserver.port=${SERVER_PORT} \
	-Dpcp.webservice.soap.uri=${PCP_WS_SOAP_URI} \
	-Dservice.name=${SERVICE_NAME} \
	-Dservice.instance.id=${SERVICE_INSTANCE_ID} \
	-Dserver.monitor.file=${SERVICE_MONITOR_FILE} \
	-Dmanagement.endpoint.health.enabled=${MANAGEMENT_ENDPOINT_HEALTH_ENABLED} \
	-Dmanagement.endpoint.health.show-details=${MANAGEMENT_ENDPOINT_HEALTH_SHOW_DETAILS} \
	-Dspring.datasource.jdbcUrl=${DATASOURCE_URL} \
    -Dspring.datasource.username=${DATASOURCE_USERNAME} \
    -Dspring.datasource.password=${DATASOURCE_PASSWORD} \
	-Dspring.datasource.hikari.connection-timeout=${DATASOURCE_CONNECTION_TIMEOUT} \
    -Dspring.datasource.driverClassName=${DATASOURCE_DRIVER_CLASS} \
    -Dspring.jpa.hibernate.naming.implicit-strategy=${JPA_NAMING_IMPLICIT_STRATEGY} \
    -Dspring.jpa.hibernate.naming.physical-strategy=${JPA_NAMING_PHYSICAL_STRATEGY} \
	-Dspring.jpa.hibernate.c3p0.min.size=${JPA_MIN_SIZE} \
    -Dspring.jpa.hibernate.c3p0.max.size=${JPA_MAX_SIZE} \
    -Dspring.jpa.hibernate.c3p0.timeout=${JPA_TIMEOUT} \
	-Da3.datasource.jdbcUrl=${A3_DATASOURCE_JDBCURL} \
    -Da3.datasource.username=${A3_DATASOURCE_USERNAME} \
    -Da3.datasource.password=${A3_DATASOURCE_PASSWORD} \
    -Da3.datasource.driverClassName=${A3_DATASOURCE_DRIVERCLASSNAME} \
    -Da3.datasource.hibernate.dialect=${A3_DATASOURCE_HIBERNATE_DIALECT} \
    -Djavax.net.ssl.trustStore=/usr/java/latest/jre/lib/security/cacerts \
	-jar /pcp-search-service-1.0.0.jar
