package topologies.wordcounter

import org.apache.storm.task.OutputCollector
import org.apache.storm.task.TopologyContext
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseRichBolt
import org.apache.storm.tuple.Tuple
import java.io.PrintWriter

class WordCountBolt: BaseRichBolt() {

    private lateinit var wordCount: MutableMap<String, Int>
    private lateinit var taskId: Number
    private lateinit var name: String
    private lateinit var fileName: String

    override fun declareOutputFields(declarer: OutputFieldsDeclarer?) {}

    override fun prepare(topoConf: MutableMap<String, Any>?, context: TopologyContext?, collector: OutputCollector?) {
        val outputDir = topoConf!!["outputDirectory"].toString()
        this.wordCount = HashMap()
        this.name = context!!.thisComponentId
        this.taskId = context!!.thisTaskId
        this.fileName = "$outputDir/output-$name-$taskId.txt"
    }

    override fun execute(input: Tuple?) {
        val word = input!!.getStringByField("word")
        if(wordCount.containsKey(word)) {
            wordCount[word] = wordCount[word]!! + 1
        } else {
            wordCount[word] = 1
        }
    }

    override fun cleanup() {
        val writer = PrintWriter(fileName, "UTF-8")
        wordCount.forEach {
            writer.println("${it.key}: ${it.value}")
        }
        writer.close()
    }
}
