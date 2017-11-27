package org.apache.spark.ml.util

import org.apache.spark.ml.evaluation.BinaryClassificationEvaluator
import org.apache.spark.ml.feature.Word2VecModel
import org.apache.spark.sql.SparkSession

object Word2Vec extends App {

  val spark = SparkSession.builder()
    .appName("Attribute Analysis")
    .master("local")
    .getOrCreate()
//
  // Input data: Each row is a bag of words from a sentence or document.
  val documentDF = spark.createDataFrame(Seq(
    "Hi I heard about Spark".split(" "),
    "I wish Java could use case classes".split(" "),
    "Logistic regression models are neat".split(" ")
  ).map(Tuple1.apply)).toDF("text")

//  // Learn a mapping from words to Vectors.
//  val word2Vec = new Word2Vec()
//    .setInputCol("text")
//    .setOutputCol("result")
//    .setVectorSize(3)
//    .setMinCount(0)
//  val model = word2Vec.fit(documentDF)
//
//  word2Vec.write.overwrite().save("/tmp/word2vec-transformation")
//  model.write.overwrite().save("/tmp/word2vec-model")
  val modelLoaded = Word2VecModel.read.load("/tmp/word2vec-model")
  val metadata = DefaultParamsReader.loadMetadata("/tmp/word2vec-model", spark.sparkContext)
  if (metadata.className.contains(Word2VecModel)) {

  }



  println(modelLoaded)

  val evaluator = new BinaryClassificationEvaluator()
    .setLabelCol("SurvivedIndexed")
    .setMetricName("areaUnderROC")

  private val d: Double = evaluator.evaluate(documentDF)

  evaluator.write.overwrite().save("/tmp/evaluator")

  //val loadeModel =

//  val result = model.transform(documentDF)
//  result.collect().foreach { case Row(text: Seq[_], features: Vector) =>
//    println(s"Text: [${text.mkString(", ")}] => \nVector: $features\n") }

}
