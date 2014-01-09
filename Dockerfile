# BoatCraft
#
# VERSION       2.0 Beta

# use the ubuntu base
FROM base

MAINTAINER Kepler Sticka-Jones, kbsj269@gmail.com

# Build using Gradle
ENTRYPOINT ["./gradlew", "setupCIWorkspace"]
ENTRYPOINT ["./gradlew", "buildNeeded"]
