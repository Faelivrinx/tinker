# Tinker

## Building

Run the app using docker image:

```bash
./gradlew bootBuildImage
docker run -p 8080:8080 tinker:0.0.1-SNAPSHOT
```

Run the app using gradle:

```bash
./gradlew bootRun
```

## TODO

- [x] model a database schema
