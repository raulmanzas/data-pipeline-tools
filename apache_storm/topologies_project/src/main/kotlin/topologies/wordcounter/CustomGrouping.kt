package topologies.wordcounter

import org.apache.storm.generated.GlobalStreamId
import org.apache.storm.grouping.CustomStreamGrouping
import org.apache.storm.task.WorkerTopologyContext
import java.io.Serializable

class CustomGrouping: CustomStreamGrouping, Serializable {

    private lateinit var targetTasks: MutableList<Int>

    override fun prepare(context: WorkerTopologyContext?, stream: GlobalStreamId?, targetTasks: MutableList<Int>?) {
        this.targetTasks = targetTasks!!
    }

    override fun chooseTasks(taskId: Int, values: MutableList<Any>?): MutableList<Int> {
        val bolts = mutableListOf<Int>()
        val word = values!![0].toString()
        if (word.startsWith("a")) {
            bolts.add(targetTasks[0])
        } else {
            bolts.add(targetTasks[1])
        }
        return bolts
    }
}