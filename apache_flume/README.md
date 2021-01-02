# Apache Flume

Trying data ingestion using Apache Flume. Check the [official docs](https://flume.apache.org/releases/content/1.9.0/FlumeUserGuide.html) to learn how it works.

### How to use
Add agents conf files to /agents dir, and build the image:

```bash
$ docker build -t test_flume .
```

Then, run a container passing the desired agent's .conf file:
```bash
$ docker run -p <relevant_port>:<relevant_port> test_flume <desired_agent_file.conf> <desired_agent_name>
```
