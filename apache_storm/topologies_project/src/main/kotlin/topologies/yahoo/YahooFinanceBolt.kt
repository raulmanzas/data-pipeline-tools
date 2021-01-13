package topologies.yahoo

import org.apache.storm.topology.BasicOutputCollector
import org.apache.storm.topology.OutputFieldsDeclarer
import org.apache.storm.topology.base.BaseBasicBolt
import org.apache.storm.tuple.Tuple

class YahooFinanceBolt: BaseBasicBolt() {
    override fun declareOutputFields(declarer: OutputFieldsDeclarer?) {
    }

    override fun execute(input: Tuple?, collector: BasicOutputCollector?) {
        val company = input!!.getValueByField("company").toString()
        val timestamp = input!!.getValueByField("timestamp")
        val price = input!!.getValueByField("price") as Double
        val previousClose = input!!.getValueByField("previousClose") as Double

        if (price <= previousClose) {
            info("($timestamp) -> $company is worth less now than yesterday")
        } else {
            info("($timestamp) -> $company is worth more than yesterday")
        }
        info("Current price is $price")
    }

    private fun info(message: String) {
        println(">> $message")
    }
}