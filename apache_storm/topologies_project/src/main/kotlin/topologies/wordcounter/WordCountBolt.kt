package topologies.wordcounter

import org.apache.storm.task.OutputCollector
import org.apache.storm.task.TopologyContext
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseRichBolt
import org.apache.storm.tuple.Tuple

class WordCountBolt: BaseRichBolt() {
    override fun declareOutputFields(declarer: OutputFieldsDeclarer?) {
        TODO("Not yet implemented")
    }

    override fun prepare(topoConf: MutableMap<String, Any>?, context: TopologyContext?, collector: OutputCollector?) {
        TODO("Not yet implemented")
    }

    override fun execute(input: Tuple?) {
        TODO("Not yet implemented")
    }

}
