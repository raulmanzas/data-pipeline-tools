package topologies.exclamation

import org.apache.storm.LocalCluster

fun main() {
    val exclamationTopology = ExclamationTopology()
    LocalCluster().submitTopology(
        exclamationTopology.getName(),
        exclamationTopology.getConfig(),
        exclamationTopology.getBuilder().createTopology()
    )
}