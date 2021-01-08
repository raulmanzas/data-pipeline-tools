package topologies.exclamation

import org.apache.storm.Config
import org.apache.storm.testing.TestWordSpout
import org.apache.storm.topology.TopologyBuilder

class ExclamationTopology {

    fun getName(): String {
        return "ExclamationTopology"
    }

    fun getBuilder(): TopologyBuilder {
        return TopologyBuilder().apply {
            setSpout("word", TestWordSpout(), 10)
            setBolt("exclamation1", ExclamationBolt(), 3).shuffleGrouping("word")
            setBolt("exclamation2", ExclamationBolt(), 2).shuffleGrouping("exclamation1")
        }
    }

    fun getConfig(): Config {
        return Config().apply {
            setDebug(true)
        }
    }
}
