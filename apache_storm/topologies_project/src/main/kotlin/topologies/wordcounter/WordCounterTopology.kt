package topologies.wordcounter

import org.apache.storm.Config
import org.apache.storm.topology.TopologyBuilder

class WordCounterTopology  {

    fun getName(): String {
        return "WordCounterTopology"
    }

    /***
     * There are 4 relevant groupings to try with this topology and see
     * how each affects the result
     */
    fun getBuilder(): TopologyBuilder {
        return TopologyBuilder().apply {
            setSpout("readWords", WordSpout())
            setBolt("wordCounter", WordCountBolt(), 2)
//                .customGrouping("readWords", CustomGrouping())
//                .allGrouping("readWords")
//                .fieldsGrouping("readWords", Fields("word"))
                .shuffleGrouping("readWords")
        }
    }

    fun getConfig(): Config {
        return Config().apply {
            setDebug(true)
            put("inputPath", "src/main/resources/words_input.txt")
            put("outputDirectory", ".words_output/")
        }
    }
}
