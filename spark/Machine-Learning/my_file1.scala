
import scala.math._
import scala.collection.mutable.ArrayBuffer
import java.io.PrintWriter
import scala.io.Source
import org.apache.spark._
import org.apache.spark.graphx._
import org.apache.spark.rdd.RDD

object Thanks {

  def main(args: Array[String]): Unit = {
    var friends=Array("Mitch", "Alex")

    for(i <-0 to 2)
    {
      println("Thanks guys " +friends)
    }

  }