#ARG DELTA_DOCKER_REGISTRY=dockerdev.deltads.ent:7070
#ARG BASE_IMAGE_NAME=delta
#ARG BASE_IMAGE_TAG=jdk11-jre-alpine-appd-contrast

FROM openjdk:11-jre

MAINTAINER Delta Dental Group Member Online - DCM
VOLUME /tmp
ADD pcp-search-service-3.0.0.jar pcp-search-service-3.0.0.jar
ADD contrast.jar contrast.jar
RUN sh -c 'touch /pcp-search-service-3.0.0.jar'
RUN sh -c 'touch /contrast.jar'
ENV JAVA_OPTS=""
ENTRYPOINT java $JAVA_OPTS \
	-javaagent:$APPD_ROOT/javaagent.jar \
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
	-Dservice.name=${SERVICE_NAME} \
	-Dexternal.ciam.authz.service.base.url=${EXTERNAL_CIAM_AUTHZ_SERVICE_BASE_URL} \
	-Dfile.polling.directory=${FILE_POLLING_DIRECTORY} \
	-Dfile.success.directory=${FILE_SUCCESS_DIRECTORY} \
	-Dfile.fail.directory=${FILE_FAIL_DIRECTORY} \
	-Dfile.processing.workers.count=${FILE_PROCESSING_WORKERS_COUNT} \
	-Dfile.processing.threshold.numberofretries=${FILE_PROCESSING_THRESHOLD_NUMBEROFRETRIES} \
	-Dfile.polling.interval.minutes=${FILE_POLLING_INTERVAL_MINUTES} \
	-Dgroup.invoice.service.url=${GROUP_INVOICE_SERVICE_URL} \
	-Dpublishing.and.merging.job.initial.delay=${PUBLISHING_AND_MERGING_JOB_INITIAL_DELAY} \
	-Dscheduling.job.initial.delay=${SCHEDULING_JOB_INITIAL_DELAY} \
	-Dpublishing.and.merging.job.fixed.delay=${PUBLISHING_AND_MERGING_JOB_FIXED_DELAY} \
	-Dscheduling.job.fixed.delay=${SCHEDULING_JOB_FIXED_DELAY} \
	-Dservice.cert.file=${DD_KEYSTORE_FILE} \
	-Dservice.cert.password=${DD_KEYSTORE_PASSWORD} \
	-Dservice.oauth.token.url=${SERVICE_OAUTH_TOKEN_URL} \
	-Dservice.oauth.client.key=${SERVICE_OAUTH_CLIENT_KEY} \
	-Dservice.oauth.client.secret=${SERVICE_OAUTH_CLIENT_SECRET} \
	-Dservice.oauth.initial.delay=${SERVICE_OAUTH_INITIAL_DELAY} \
	-Dservice.oauth.fixed.delay=${SERVICE_OAUTH_FIXED_DELAY} \
	-Dservice.instance.id=${SERVICE_INSTANCE_ID} \
	-Dserver.monitor.file=${SERVICE_MONITOR_FILE} \
	-Djava.security.egd=file:/dev/./urandom \
	-Dspring.datasource.url=${DATASOURCE_URL} \
	-Dspring.datasource.username=${DATASOURCE_USERNAME} \
	-Dspring.datasource.password=${DATASOURCE_PASSWORD} \
	-Dspring.datasource.hikari.connection-timeout=${DATASOURCE_CONNECTION_TIMEOUT} \
	-Dspring.datasource.driverClassName=${DATASOURCE_DRIVER_CLASS} \
	-Djavax.net.ssl.trustStore=/usr/java/latest/jre/lib/security/cacerts \
	-Dmanagement.endpoints.web.exposure.include=${MANAGEMENT_ENDPOINT_WEB_EXPOSURE_INCLUDE} \
	-Dendpoints.health.sensitive=${ENDPOINDS_HEALTH_SENSITIVE} \
	-Dmanagement.security.enabled=${MANAGEMENT_SECURITY_ENABLED} \
	-Dmanagement.endpoints.enabled-by-default=${MANAGEMENT_ENDPOINTS_ENABLED_BY_DEFAULT} \
	-Dmanagement.endpoints.web.path-mapping.health=${MANAGEMENT_ENDPOINTS_WEB_PATH_MAPPING_HEALTH} \
	-Dmanagement.endpoint.health.enabled=${MANAGEMENT_ENDPOINT_HEALTH_ENABLED} \
	-Dmanagement.endpoint.health.show-details=${MANAGEMENT_ENDPOINT_HEALTH_SHOW_DETAILS} \
	-Denvironment=${ENVIRONMENT} \
	-Dtoken.validation.enabled=false \
  -Drequest.validation.enabled=false \
  -Drequest.payload.validation.enabled=false \
  -Dresponse.payload.validation.enabled=false \
  -Dinvoice.publish.batch.size=${INVOICE_PUBLISH_BATCH_SIZE} \
	-Dinternal.authentication.url=${INTERNAL_AUTHENTICATION_URL} \
	-Dinternal.authorization.url=${INTERNAL_AUTHORIZATION_URL} \
	-Dinternal.authorization.appId=${INTERNAL_AUTHORIZATION_APPID} \
	-Dinternal.authorization.brand=${INTERNAL_AUTHORIZATION_BRAND} \
	-Dinternal.user.name=${INTERNAL_USER_NAME} \
	-Dinternal.user.password=${INTERNAL_USER_PASSWORD} \
	-Dplatform.pdf.document.url=${PLATFORM_PDF_DOCUMENT_URL} \
	-Dserver.compression.enabled=${TOMCAT_SERVER_COMPRESSION_ENABLED} \
  -Dserver.compression.mime-types=${TOMCAT_SERVER_COMPRESSION_MIME_TYPES} \
  -Dserver.compression.min-response-size=${TOMCAT_SERVER_COMPRESSION_MIN_RESPONSE_SIZE} \
	-jar /pcp-search-service-3.0.0.jar