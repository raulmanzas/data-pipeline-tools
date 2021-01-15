package topologies.wordcounter

import org.apache.storm.LocalCluster

fun main() {
    val wordCounterTopology = WordCounterTopology()
    val cluster = LocalCluster()
    cluster.submitTopology(
        wordCounterTopology.getName(),
        wordCounterTopology.getConfig(),
        wordCounterTopology.getBuilder().createTopology()
    )
    Thread.sleep(5000)
    cluster.close()
}
