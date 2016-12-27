/*
 * Licensed to Intel Corporation under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * Intel Corporation licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.intel.analytics.bigdl.models.resnet

import scopt.OptionParser

object Options {
  case class TrainLocalParams(
  folder: String = "./",
  checkpoint: Option[String] = None,
  modelSnapshot: Option[String] = None,
  stateSnapshot: Option[String] = None,
  optnet: Boolean = false,
  dataset: String = "",
  coreNumber: Int = (Runtime.getRuntime().availableProcessors() / 2))

  val trainLocalParser = new OptionParser[TrainLocalParams]("BigDL ResNet Example") {
    head("Train ResNet model on single node")
    opt[String]('f', "folder")
      .text("where you put your local hadoop sequence files")
      .action((x, c) => c.copy(folder = x))
    opt[String]("model")
      .text("model snapshot location")
      .action((x, c) => c.copy(modelSnapshot = Some(x)))
    opt[String]("state")
      .text("state snapshot location")
      .action((x, c) => c.copy(stateSnapshot = Some(x)))
    opt[Int]('c', "core")
      .text("cores number to train the model")
      .action((x, c) => c.copy(coreNumber = x))
    opt[String]("cache")
      .text("where to cache the model")
      .action((x, c) => c.copy(checkpoint = Some(x)))
    opt[Boolean]("optnet")
      .text("shared gradients and caches to reduce memory usage")
      .action((x, c) => c.copy(optnet = x))
    opt[String]("dataset")
      .text("datasets: imagenet | cifar-10")
      .action((x, c) => c.copy(dataset = x))
  }

  case class TrainSparkParams(
     folder: String = "./",
     cache: Option[String] = None,
     modelSnapshot: Option[String] = None,
     stateSnapshot: Option[String] = None,
     coreNumberPerNode: Int = -1,
     nodesNumber: Int = -1,
     optnet: Boolean = false,
     dataset: String = "",
     coreNumber: Int = (Runtime.getRuntime().availableProcessors() / 2))

  val trainSparkParser = new OptionParser[TrainSparkParams]("BigDL ResNet Example") {
    head("Train ResNet model on Apache Spark")
    opt[String]('f', "folder")
      .text("where you put the data")
      .action((x, c) => c.copy(folder = x))
    opt[String]("model")
      .text("model snapshot location")
      .action((x, c) => c.copy(modelSnapshot = Some(x)))
    opt[String]("state")
      .text("state snapshot location")
      .action((x, c) => c.copy(stateSnapshot = Some(x)))
    opt[String]("cache")
      .text("where to cache the model")
      .action((x, c) => c.copy(cache = Some(x)))
    opt[Int]('c', "core")
      .text("cores number on each node")
      .action((x, c) => c.copy(coreNumberPerNode = x))
      .required()
    opt[Int]('n', "nodeNumber")
      .text("nodes number to train the model")
      .action((x, c) => c.copy(nodesNumber = x))
      .required()
    opt[Boolean]("optnet")
      .text("shared gradients and caches to reduce memory usage")
      .action((x, c) => c.copy(optnet = x))
    opt[String]("dataset")
      .text("datasets: imagenet | cifar-10")
      .action((x, c) => c.copy(dataset = x))
  }

}