package topologies.yahoo

import org.apache.storm.Config
import org.apache.storm.topology.TopologyBuilder

class YahooFinanceTopology  {

    fun getName(): String {
        return "YahooFinanceTopology"
    }

    fun getBuilder(): TopologyBuilder {
        return TopologyBuilder().apply {
            setSpout("yahooFinanceSpout", YahooFinanceSpout())
            setBolt("yahooFinanceBolt", YahooFinanceBolt()).shuffleGrouping("yahooFinanceSpout")
        }
    }

    fun getConfig(): Config {
        return Config().apply {
            setDebug(true)
        }
    }
}