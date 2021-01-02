#!/bin/sh

# This script allows parameterized agent runs by allowing AGENT_CONFIG_PATH
# and AGENT_NAME to be passed as arguments (on docker run)

FLUME_PATH=/opt/flume

# Path to a .conf file that defines an agent. It should be one of the available in /agents directory
AGENT_CONFIG_PATH=$1

# Name of the agent to run, matching the name defined on .conf file
AGENT_NAME=$2

echo ">>> Starting agent: '${AGENT_NAME}' from '${AGENT_CONFIG_PATH}'..."

$FLUME_PATH/bin/flume-ng agent \
    --conf $FLUME_PATH/conf \
    --conf-file $AGENT_CONFIG_PATH/ \
    --name $AGENT_NAME \
    -Dflume.root.logger=INFO,console \
