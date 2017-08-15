#!/bin/bash

APPLICATION_HOME=$(cd `dirname $0` && cd .. && pwd)

source $APPLICATION_HOME/bin/setenv.sh

# move to home
cd "$APPLICATION_HOME"

# build classpath
CLASSPATH=""
FIRST=0

for jar in lib/*.jar
do
  if [ "$FIRST" -eq "0" ]
  then
    CLASSPATH=$jar
    FIRST=1
  else
    CLASSPATH=$CLASSPATH:$jar
  fi
done

# build final command
COMMAND="$JAVACMD $DEFAULT_JVM_OPTS -classpath $CLASSPATH $JAVA_MAIN_CLASS"

if [ "$1" = "start" ] ; then

  if [ -f "$PID_FILE" ]; then
    echo "pid file found at $PID_FILE. Did you already started the application?"
    exit 0;
  fi

  nohup $COMMAND > /dev/null 2>&1 &

  # wait for boot
  for i in {0..10}
  do

    if [ -f "$PID_FILE" ]; then
      break;
    fi

    echo "Waiting to start"
    sleep 1
  done

  if [ ! -f "$PID_FILE" ]; then
    echo "Pid file not found. Application was not started successfully!"
    exit 1;
  fi

  # get running pid
  PID=$(cat $PID_FILE)

  echo "Started Application with process PID: $PID"
  exit 0;

elif [ "$1" = "stop" ]; then

  # check pid
  if [ ! -f "$PID_FILE" ]; then
    echo "pid file not found at $PID_FILE. Did you started the application first?"
    exit 0;
  fi

  # Get PID of running process
  PID=$(cat $PID_FILE)

  # check if this process is still active
  RESULT=$(ps -ef | grep "app.name=$APPLICATION_MARKER" | grep $PID)
  if [ "${#RESULT}" -eq 0 ]; then
    echo "Application process not found with PID: $PID. Maybe it already terminated?";
  else
    kill $PID
  fi;

  for i in {0..10}
  do

    if [ ! -f "$PID_FILE" ]; then
      echo "Application stopped!"
      break;
    fi

    echo "Waiting for application to stop"
    sleep 1
  done

  exit 0;

elif [ "$1" = "status" ]; then

  if [ ! -f "$PID_FILE" ]; then
    echo "Application is not running";
    exit 0;
  fi

  # Get PID of running process
  PID=$(cat $PID_FILE);

  # check if this process is still active
  RESULT=$(ps -ef | grep "app.name=$APPLICATION_MARKER" | grep $PID);
  if [ "${#RESULT}" -eq 0 ]; then
    echo "Application is not running";
  else
    echo "Application is running";
  fi;

  exit 0;
else
  echo "Invalid command. Usage $0 start|stop|status";
  exit 1;
fi

exit 0;
