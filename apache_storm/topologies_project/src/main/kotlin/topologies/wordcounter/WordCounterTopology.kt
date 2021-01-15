package topologies.wordcounter

import org.apache.storm.Config
import org.apache.storm.topology.TopologyBuilder
import org.apache.storm.tuple.Fields
import java.nio.file.Paths

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
//                .shuffleGrouping("readWords")
                .fieldsGrouping("readWords", Fields("word"))
        }
    }

    fun getConfig(): Config {
        val basePath = Paths.get("").toAbsolutePath().toString()
        return Config().apply {
            setDebug(true)
            put("inputPath", "$basePath/words/words_input.txt")
            put("outputDirectory", "$basePath/words")
        }
    }
}
