//kmeans streaming libraries
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.clustering.StreamingKMeans
//streaming libraries
import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._

object StreamingKMeans {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local[2]").setAppName("kmeans_app1").set("spark.driver.allowMultipleContexts", "true")
    val ssc = new StreamingContext(conf, Seconds(1))

    val trainingData = ssc.textFileStream("/user/kmeans/trainingData/learning_data.txt").map(Vectors.parse)
    // data to train the algorithm
    val testData = ssc.textFileStream("/user/kmeans/testingData").map(LabeledPoint.parse)
    // new data to put into cluster
    val numDimensions = 3
    val numClusters = 2
    val model = new StreamingKMeans().setK(numClusters).setDecayFactor(1.0).setRandomCenters(numDimensions, 0.0)
    model.trainOn(trainingData)
    model.predictOnValues(testData.map(lp => (lp.label, lp.features))).print()
    ssc.start()
    ssc.awaitTermination()
  }
}
