object taxes2 {
  def main(args: Array[String]): Unit = {
    println("what is your salary?")
    val salary = readInt()
   if(salary >= 100000){
     val z : Double = 0.55
    val y= salary*z
    print(y)
  }
    if(salary<=100000 && salary >= 50000){
      val z : Double = 0.65
      val y= salary*z
      print(y)
    }
    if(salary<=50000 && salary >= 30000) {
      val z: Double = 0.75
      val y = salary * z
      print(y)
    }
    if(salary<=30000 && salary >= 0) {
      val z: Double = 0.85
      val y = salary * z
      print(y)
    }

}

}

