package twitter

object Jsonfilter {

}
val file = new File("/home/hadoop/Downloads")
val JsonContent = FileUtils.readFileToString(file)
JSON.globalNumberParser = {input : String => Integer.parse(input)}
val jsonArray = jsonContent.split("\n").map(x=>JSON.parseFull(x).get.asInstanceOf[Map[String,String]])