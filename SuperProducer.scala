import java.util.Properties

import org.apache.kafka.clients.producer._


object SuperProducer {


  def main(args: Array[String]): Unit = {

   val result = url
println(result)
    val props: Properties = new Properties()
    props.put("bootstrap.servers","localhost:9092")
    props.put("metadata.broker.list", "localhost:9092")
    props.put("ack", "all")
    props.put("retries", "0")
    props.put("batch.size", "16384")
    props.put("linger.s", "300")
    props.put("buffer.memory", "33554432")
    props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer","org.apache.kafka.common.serialization.StringSerialization")


  val producer = new KafkaProducer[String,String](props)
  val topic="Kafkatopic"

    for(i<- 1 to 50) {
      val record = new ProducerRecord(topic, "key" + i, "value" + i)
      producer.send(record)

      }

    }

//println(stream)






}
