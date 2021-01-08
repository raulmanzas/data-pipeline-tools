package topologies.exclamation

import org.apache.storm.task.OutputCollector
import org.apache.storm.task.TopologyContext
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseRichBolt
import org.apache.storm.tuple.Fields
import org.apache.storm.tuple.Tuple
import org.apache.storm.tuple.Values

/*
 This is the "Hello world" bolt, it basically adds exclamations to a string
 */

class ExclamationBolt: BaseRichBolt() {
    private lateinit var outputCollector: OutputCollector

    override fun prepare(topoConf: MutableMap<String, Any>?, context: TopologyContext?, collector: OutputCollector?) {
        outputCollector = collector!!
    }

    override fun execute(input: Tuple?) {
        val processedInput = "${input?.getString(0)}!!"
        outputCollector.emit(input, Values(processedInput))
        outputCollector.ack(input)
    }

    override fun declareOutputFields(declarer: OutputFieldsDeclarer?) {
        declarer?.declare(Fields("word"))
    }
}
