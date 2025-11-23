# Deployment (Docker and CI/CD)

This document centralizes all deployment-related instructions. It covers how to run the game in Docker on Linux and how the GitHub Actions CI workflow builds and tests the project, with an optional Docker image build.

The files involved:
- GitHub Actions workflow: `.github/workflows/ci.yml`
- Docker image build: `Dockerfile`
- Local compose run: `docker-compose.yml`

## Why Docker?

On some environments (notably Linux servers or certain IDE/headless contexts), native I/O or AWT windows may not behave as expected. Running in a Linux container allows the game to use a TTY-based terminal reliably when headless.

## Quick Start (Docker CLI)

1. Build the image
   - `docker build -t hollowknight .`
2. Run interactively (allocate a TTY)
   - `docker run --rm -it hollowknight`

Notes
- The app runs in headless/TTY mode inside the container (no AWT window). Use an interactive terminal.

## Using Docker Compose

You can use the provided `docker-compose.yml` in the repository root.

Commands
1. Start: `docker compose up --build`
2. Stop: `docker compose down`

docker-compose.yml (for reference)
```
services:
  game:
    build: .
    image: hollowknight:local
    container_name: hollowknight
    tty: true            # Allocate a TTY so Lanterna can use the terminal
    stdin_open: true     # Keep STDIN open for key events
    environment:
      - TERM=xterm-256color
```

## CI/CD Overview (GitHub Actions + Docker)

The repository includes a CI workflow that runs on every push and pull request:

- Builds and tests the project on Ubuntu with Temurin JDK 21
- Uploads the HTML test report as an artifact
- On non-PR events (e.g., direct push), it also builds the Docker image (no push by default)

Workflow file: `.github/workflows/ci.yml`

### Schema

```mermaid
flowchart LR
  A[Developer Push / PR] --> B[CI: Build & Test (JDK 21)
     - Gradle clean build test
     - JaCoCo report]
  B --> C[Artifacts: Test Report]
  B --> D{Event is PR?}
  D -- Yes --> E[End (no Docker build)]
  D -- No (push) --> F[Build Docker image (local tag)
     - docker/build-push-action (no push)]
  F --> G[Developer can run locally via
     docker run / docker compose]
```

## macOS vs Linux

- macOS: Running directly via Gradle/IntelliJ is recommended (AWT terminal window). Docker is optional.
- Linux/headless: Docker provides a consistent TTY environment. The runtime in this repo detects headless and avoids AWT calls when running in the container.

## Gradle Tasks

- Build and test: `./gradlew clean build test`
- Fat JAR: `./gradlew shadowJar` (produces `build/libs/HollowKnight-1.0-all.jar`)

## Troubleshooting

- If you see terminal-related errors, ensure you’re running the container interactively (`-it`) or via `docker compose up` with `tty: true` and `stdin_open: true`.
- In CI, check the uploaded artifact “test-report” for detailed test output.
