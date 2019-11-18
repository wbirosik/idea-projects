object taxes {
  def main(args: Array[String]): Unit = {
    println("what is your salary?")
    val salary = readDouble()
    println("how many kids do you have?")
    val kids = readDouble()
    if(salary >= 100000){
      val z : Double = 0.55
      val y = salary*z
      println("Salary After Taxes")
      println(y)
      val t : Double = 0.05
      val q = (y*t)
      println("Tax Refund")
      println(q)
      if(kids >=1 && kids <= 3) {
      val r = (y*t)+(kids*2500)
     println("Tax Refund Plus Kids Bonus")
     println(r)     }
      if(kids >3) {
        val r = (y*t)+7500
      println("Tax Refund Plus Kids Bonus")
       println(r)   }

    }
    if(salary >=  50000 && salary <= 100000) {
      val z: Double = 0.65
      val y = salary * z
      println("Salary After Taxes")
      println(y)
      val t: Double = 0.07
      val q = (y * t)
      println("Tax Refund")
      println(q)
      if(kids >=1 && kids <= 3) {
         val r = (y*t)+(kids*2500)
        println("Tax Refund Plus Kids Bonus")
        println(r)     }
         if(kids >3) {
           val r = (y*t)+7500
         println("Tax Refund Plus Kids Bonus")
          println(r)   }



    }


    if(salary >= 30000 && salary <= 50000) {
      val z: Double = 0.75
      val y = salary * z
      println("Salary After Taxes")
      println(y)
      val t: Double = 0.10
      val q = (y * t)
      println("Tax Refund")
      println(q)




          if(kids >=1 && kids <= 3) {
          val r = (y*t)+(kids*2500)
         println("Tax Refund Plus Kids Bonus")
         println(r)     }
          if(kids >3) {
            val r = (y*t)+7500
          println("Tax Refund Plus Kids Bonus")
           println(r)   }

    }
    if(salary >= 0 && salary <= 30000) {
      val z: Double = 1.0
      val y = salary * z
      println("Salary After Taxes")
      println(y)
      val t: Double = 0.13
      val q = (y * t)
      println("Tax Refund")
      println(q)




         if(kids >=1 && kids <= 3) {
         val r = (y*t)+(kids*2500)
        println("Tax Refund Plus Kids Bonus")
        println(r)     }
         if(kids >3) {
           val r = (y*t)+7500                             
         println("Tax Refund Plus Kids Bonus")
          println(r)   }


    }


    if(kids>=3){
      println("Plus a free tv")

    }

  }

}
































