import org.apache.spark.mllib.clustering.BisectingKMeans
import org.apache.spark.mllib.linalg.{Vector, Vectors}


object StreamingKMeans {
  def main(args: Array[String]) {
    // Loads and parses data
    def parse(line: String): Vector = Vectors.dense(line.split(" ").map(_.toDouble)) //Parse the data from strins into vectors
    val data = sc.textFile("/user/kmeans/apple_info.txt").map(parse).cache() //Load File and start parsing

    // Clustering the data into 3 clusters by BisectingKMeans.
    val bkm = new BisectingKMeans().setK(3)
    val model = bkm.run(data)

    // Show the compute cost and the cluster centers
    println(s"Compute Cost: ${model.computeCost(data)}")
    model.clusterCenters.zipWithIndex.foreach { case (center, idx) =>
      println(s"Cluster Center ${idx}: ${center}")
    }


    //Predict which cluster new data will go into
    val new_data = sc.textFile("/user/kmeans/new_info.txt").map(parse).cache()
    model.predict(new_data).foreach(println)


  }
}