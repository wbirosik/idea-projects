package Bill

import org.apache.flink.streaming.api.scala._
import org.apache.flink.table.api._
import org.apache.flink.table.api.scala.map
import org.apache.flink.table.sources.CsvTableSource
import org.apache.flink.types.Row


object Flinkstreaming extends App{

  val env = StreamExecutionEnvironment.getExecutionEnvironment
  val tenv = TableEnvironment.getTableEnvironment(env)

  val sEnv = StreamExecutionEnvironment.getExecutionEnvironment
  val dStream: DataStream[String] = sEnv.socketTextStream(hostname = "localhost", port = 9000)

//  dStream.filter(_.length>10).map("Greater Than"+_).print()
//dStream.filter(_.length<=10).map(fun="S10>"+_).print()
//  dStream.filter(_.stri)
sEnv.execute("Flinkstreaming")


  val WordSource = CsvTableSource.builder()
    .path("/home/hadoop/Documents/bad-words.csv")
    .ignoreFirstLine()
    .fieldDelimiter("|")
    //.field("id", Types.LONG)
    .field("name", Types.STRING)
   // .field("last_update", Types.STRING)
    //.field("prefs", Types.STRING)
    .build()


  // name your table source
  tenv.registerTableSource("Bad-words", WordSource)


  val table = tenv
    .scan("Bad-words")
    dStream.filter(_.String).map(fun="bad").print()
      //
  //'last_update > "2016-01-01 00:00:00".toTimestamp)
   // .select('name.lowerCase(), 'prefs)

  // convert it to a data stream
  val ds = table.toDataStream[Row]

  ds.print()
  env.execute()




}

