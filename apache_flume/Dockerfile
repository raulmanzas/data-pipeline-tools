FROM openjdk:8-jre

RUN cd /opt && \
    wget http://ftp.unicamp.br/pub/apache/flume/stable/apache-flume-1.9.0-bin.tar.gz && \
    tar xzf apache-flume-1.9.0-bin.tar.gz && \
    mv apache-flume-1.9.0-bin flume && \
    rm apache-flume-1.9.0-bin.tar.gz && \
    apt-get update && \
    apt-get install telnet

# Loads the agent-runner script along with all agents defined in /agents.
# This way the container gets reused for whatever agent necessary.
COPY agent-runner.sh /flume/agent-runner.sh
COPY agents/ /flume/agents

RUN chmod +x /flume/agent-runner.sh

# This is here just to indicate the sample "netcat_to_console" agent port.
EXPOSE 44444

ENTRYPOINT ["/bin/sh", "/flume/agent-runner.sh"]

CMD ["flume/agents/netcat_to_console.conf", "agent"]
