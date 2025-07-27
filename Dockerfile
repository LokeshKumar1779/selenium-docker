FROM bellsoft/liberica-openjdk-alpine:17.0.16

# Install curl and jq
RUN apk add --no-cache curl jq

#workspace
WORKDIR /home/selenium-docker

# Add the required files
COPY target/docker-resources ./
COPY runner.sh runner.sh


LABEL Name="selenium"
LABEL Version="1.0.0"

# Add the environment variables
# BROWSER
# HUB_HOST
# TEST_SUITE
# THREAD_COUNT

# Start the runner script
ENTRYPOINT sh runner.sh