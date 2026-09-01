#!/usr/bin/env bash
set -e

# Resolve script directory
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
WRAPPER_DIR="$SCRIPT_DIR/.mvn/wrapper"
WRAPPER_JAR="$WRAPPER_DIR/maven-wrapper.jar"

# Download the maven-wrapper JAR if missing
if [ ! -f "$WRAPPER_JAR" ]; then
  echo "Downloading Maven Wrapper jar..."
  mkdir -p "$WRAPPER_DIR"
  URL="https://repo1.maven.org/maven2/io/takari/maven-wrapper/0.5.6/maven-wrapper-0.5.6.jar"
  if command -v curl >/dev/null 2>&1; then
    curl -fsSL -o "$WRAPPER_JAR" "$URL"
  elif command -v wget >/dev/null 2>&1; then
    wget -q -O "$WRAPPER_JAR" "$URL"
  else
    echo "Error: curl or wget required to download the Maven Wrapper jar." >&2
    exit 1
  fi
fi

# Choose java
if [ -n "$JAVA_HOME" ] && [ -x "$JAVA_HOME/bin/java" ]; then
  JAVA_CMD="$JAVA_HOME/bin/java"
else
  JAVA_CMD=java
fi

# Execute the wrapper using the correct main class from the jar
exec "$JAVA_CMD" -Dmaven.multiModuleProjectDirectory="$SCRIPT_DIR" -cp "$WRAPPER_JAR" org.apache.maven.wrapper.MavenWrapperMain "$@"
