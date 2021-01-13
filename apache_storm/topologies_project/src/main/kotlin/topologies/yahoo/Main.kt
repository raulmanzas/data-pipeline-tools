import org.apache.storm.LocalCluster
import topologies.yahoo.YahooFinanceTopology

fun main() {
    val yahooFinanceTopology = YahooFinanceTopology()
    val cluster = LocalCluster()
    cluster.submitTopology(
        yahooFinanceTopology.getName(),
        yahooFinanceTopology.getConfig(),
        yahooFinanceTopology.getBuilder().createTopology()
    )
    Thread.sleep(50000)
    cluster.close()
}