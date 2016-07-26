val rawData = sc.textFile("/user/kmeans/machine_info1.txt") //Import File and partition 50
rawData.count//count input data
val dataAndLabel = rawData.map { lines=>
    val buffer = lines.split("\t").toBuffer
    buffer.remove(1)
    val label = buffer.remove(buffer.length-1)
    val vector = buffer.map(_.toDouble).toArray
    (vector, label)
  }
val data = dataAndLabel.map(_._1).cache()//tells spark its gonna use this data. So it can keep it in memory and speeden up everything

import org.apache.spark.mllib.clustering._
val model = kmeans.run(data)

val clusterLabelCount = dataAndLabel.map {
  case (data.label) => (model.predict(data).label)
}.countByValue.toList.sorted.foreach{
  case ((cluster, label), count)=>
    println(f"$cluster%1s$label%18s$count%8s")
}
