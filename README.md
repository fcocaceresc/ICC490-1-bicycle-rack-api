# Bicycle Rack API

## Prerequisites

- Java 21
- Maven (if you don't have it installed, you can replace `mvn` with `./mvnw` in the commands below)
- Docker
- act

## Getting Started

1. Clone the repository:
    ```bash
    git clone git@github.com:fcocaceresc/bicycle-rack-api.git
    ```
2. Change to the project directory:
    ```bash
    cd bicycle-rack-api
    ```

## How to run locally

1. Package the application:
    ```bash
    mvn package
    ```
2. Build the Docker image:
    ```bash
    mvn docker:build
    ```
3. Run the Docker container:
    ```bash
    mvn docker:run
    ```

## How to deploy to EC2

1. Install [act](https://github.com/nektos/act)
2. Copy the `.secrets.template` file to `.secrets`:
    ```bash
    cp .secrets.template .secrets
    ```
3. Fill in the `.secrets` file:
4. Run act:
    ```bash
    act --secret-file .secrets -P ubuntu-latest=catthehacker/ubuntu:full-latest --container-options "--group-add $(stat -c %g /var/run/docker.sock)"
    ```
