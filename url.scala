

object url  {
  object GetUrlContent extends App {

    val url = "https://api.spotify.com/v1/artists/1vCWHaC5f2uS3yhpwWbIA6/albums?album_type=SINGLE&offset=20&limit=10"
    val result = scala.io.Source.fromURL(url).mkString
    println(result)

  }






















  //  val queue = new LinkedBlockingQueue[Status](1000)
//  val consumerKey = "1442afdc63dc4c0bb9cda6391e8386c5"
//
//  val accessToken = "CeoPlmWrzNl6lVe0LOUtIuK4Xg6GpsyE "
//
//
//  val topicName = "Bill"
//  val keyword = "NFL"
//
//  val confBuild = new ConfigurationBuilder()
//
//  confBuild.setDebugEnabled(true)
//    .setOAuthConsumerKey(consumerKey)
//    .setOAuthAccessToken(accessToken)




}

  //  //def get(url: String) = scala.io.Source.fromURL(url).mkString
//  val url = "https://pokeapi.co/api/v2/pokemon?offset=100&limit=100"
//  val stream = scala.io.Source.fromURL(url).mkString
//  val queue = new LinkedBlockingQueue[Status](1000)
//
//  while (true) {
//
//    val status = queue.poll()
//
//    if (status == null) {}
//
//
//    //  "https://gateway.marvel.com:443/v1/public/characters?name=deadpool" +
//    //    "&limit=100" +
//    //    "&ts=1" +
//    //    "&hash=1d1c5188d328fc99ade6205eb8eac26792357ce0"+
//    //    "&apikey=631ae9eb90d7ab0f662a9a000a548cf3"
//    //println(url)
//
//  }
//  println(stream)
