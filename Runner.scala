import manipulators._;
import java.io._
import java.io.ByteArrayInputStream
import dstructures._

object Runner {
	//val words = collection.mutable.Map[String, String]();
	//var ngrams = collection.mutable.Map[String, List[String]]();

	def time[R](block: => R): R = {
		val t0 = System.nanoTime()
	    val result = block    // call-by-name
	    val t1 = System.nanoTime()
	    println("Elapsed time: " + (t1 - t0)/1000/1000 + "ms")
	    result
	}


	def main(args: Array[String]) {
		val V = new ReverseFeatureIndex();
		println("Loading database")
		val source = io.Source.fromFile(args(0))
		val lines =  source.getLines
		var counter = 0;
		for( line <- lines) {
			counter += 1;
			//if (counter % 1000 == 0) println(counter)
			val a = new NgramSplitter(line);
			for( ngram <- a.generate_ngrams()) {
				//println(ngram);
				V.addString(ngram, line);
			}
			
		}
		/*
		val out = new FileOutputStream("ngrams.db")
		out.write(ngrams.pickle.value)
		out.close
		*/
		println("Database loaded, contains "+counter+" entities")
		
		for (ln <- io.Source.stdin.getLines) {
			print("?: ");
			AllScan(new Ngram(ln), 3, V, 10).foreach(println(_))
			//time {fuzzysearch(ln)}
			//time {simstring(ln)}
			
		}
		

	}
	def AllScan(X: Ngram, r: Int, V:ReverseFeatureIndex, l: Int): List[CountMap] = {
		val M = new ReverseKeyCountIndex();
		val allStrings = X.ngrams().map(q => V.getStrings(q)).flatten.foreach(M ++ _);
		/*
		for(gram <- X.ngrams()) {
			println(gram)
			for(str <- V.getStrings(gram)) {
				println(str)
			}
		}
		*/
		M.ordered_keys()
	}


	def CPMerge(X: Ngram, r: Int, V:ReverseFeatureIndex, l: Int): List[String] = {
		val weights : Array[Int] = X.ngrams().map(x => {
			V.getLength(x)
			}).toArray

		X.setWeights(weights);
		val query = X.ngrams_ordered();
		query.foreach(println)

		List[String]("kissa");
	}

	/*

	def fuzzysearch(querystr: String) {
		println("Running fuzzysearch")
		val a = new StringFuzzyer(querystr);
		a.remove()
		a.add()
		a.substitute()
		for( query <- a.strings) {
			if (words contains query) println(query);
		}
		println(a.strings.length)
	}

	def simstring(querystr: String) {
		println("Running simstring")
		val a = new NgramSplitter(querystr);
		val tlist = collection.mutable.Map[String, Int]();
		for( ngram <- a.ngrams()) {
			ngrams.get(ngram) match {
				case Some(list) => list.foreach(word => {tlist += (word -> (tlist.getOrElse(word, 0)+1))})
				case None => ;
			}			
		}
		tlist.toList sortBy {-_._2} take(10) foreach {case (k, v) => println(k+": "+v)}
	}
	*/
}