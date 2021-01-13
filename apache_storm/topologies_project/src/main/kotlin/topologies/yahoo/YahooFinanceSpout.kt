package topologies.yahoo

import org.apache.storm.spout.SpoutOutputCollector
import org.apache.storm.task.TopologyContext
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseRichSpout
import org.apache.storm.tuple.Fields
import org.apache.storm.tuple.Values
import yahoofinance.YahooFinance
import java.time.LocalDateTime

const val GOOGLE_STOCK = "GOOG"

class YahooFinanceSpout: BaseRichSpout() {

    private lateinit var collector: SpoutOutputCollector

    override fun declareOutputFields(declarer: OutputFieldsDeclarer?) {
        declarer!!.declare(Fields("company", "timestamp", "price", "previousClose"))
    }

    override fun open(conf: MutableMap<String, Any>?, context: TopologyContext?, collector: SpoutOutputCollector?) {
        this.collector = collector!!
    }

    override fun nextTuple() {
        val quote = YahooFinance.get(GOOGLE_STOCK).quote

        collector.emit(Values(
            GOOGLE_STOCK,
            LocalDateTime.now(),
            quote.price.toDouble(),
            quote.previousClose.toDouble()
        ))
        // just to avoid rate limit
        Thread.sleep(2000)
    }
}
