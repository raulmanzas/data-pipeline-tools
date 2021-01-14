package topologies.wordcounter

import org.apache.storm.spout.SpoutOutputCollector
import org.apache.storm.task.TopologyContext
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseRichSpout
import org.apache.storm.tuple.Fields
import org.apache.storm.tuple.Values
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.FileReader

class WordSpout: BaseRichSpout() {

    private lateinit var collector: SpoutOutputCollector
    private lateinit var fileReader: FileReader
    private lateinit var bufferedReader: BufferedReader
    private var finished = false

    override fun declareOutputFields(declarer: OutputFieldsDeclarer?) {
        declarer!!.declare(Fields("word"))
    }

    override fun open(conf: MutableMap<String, Any>?, context: TopologyContext?, collector: SpoutOutputCollector?) {
        this.collector = collector!!
        val inputPath = conf!!["inputPath"].toString()

        try {
            fileReader = FileReader(inputPath)
            bufferedReader = BufferedReader(fileReader)
        } catch (e: FileNotFoundException) {
            throw RuntimeException("Error reading $inputPath file. Is the path correct?")
        }
    }

    override fun nextTuple() {
        if(finished) return
        try {
            val word = bufferedReader.readLine()
            if (word.isNullOrEmpty()) {
                finished = true
                fileReader.close()
            }

            collector.emit(Values(word.trim().toLowerCase()))
        } catch (e: Exception) {
            throw RuntimeException("Error parsing word", e)
        }
    }
}
