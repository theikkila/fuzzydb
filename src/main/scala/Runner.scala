import manipulators._;
import java.io._
import java.io.ByteArrayInputStream
import dstructures._

object Runner extends App {
	//val words = collection.mutable.Map[String, String]();
	//var ngrams = collection.mutable.Map[String, List[String]]();

	def time[R](block: => R): R = {
		val t0 = System.nanoTime()
	    val result = block    // call-by-name
	    val t1 = System.nanoTime()
	    println("Elapsed time: " + (t1 - t0)/1000/1000 + "ms")
	    result
	}


	override def main(args: Array[String]) {
		val V = new ReverseFeatureIndex();
		if(args.length <= 0){
			println("ERROR: give database file: run <database.txt>")
			return System.exit(1);
		}
		
		val source = io.Source.fromFile(args(0))
		println("Loading database")
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
			//Search(ln, 0.7, V, AllScan).foreach(println(_))
			Search(ln, 0.7, V, AllScan).foreach(println(_))
			//time {fuzzysearch(ln)}
			//time {simstring(ln)}
			
		}
		

	}
	def Search(query: String, a: Double, V:ReverseFeatureIndex, overlapjoin: (List[String], Int, ReverseFeatureIndex, Int) => List[CountMap]): List[CountMap] = {
		// Generating features and search range(min->max)
		val xq = new NgramSplitter(query);
		val X = xq.generate_ngrams();
		var Y = List[CountMap]();
		val (search_min, search_max) = xq.search_range(a);
		for( l <- search_min to search_max) {
			val r = xq.min_overlap(a, l);
			val R = overlapjoin(X, r, V, l);
			R.foreach(st => { Y = Y ::: List[CountMap](st); })
		}
		Y
	}
	//AllScan(new Ngram(ln), 3, V, 10).foreach(println(_))

	def AllScan(X: List[String], r: Int, V:ReverseFeatureIndex, l: Int): List[CountMap] = {
		val M = new ReverseKeyCountIndex();
		val allStrings = X.map(q => V.getStringsOfLength(q, l)).flatten.foreach(M ++ _);
		/*
		for(gram <- X.ngrams()) {
			println(gram)
			for(str <- V.getStrings(gram)) {
				println(str)
			}
		}
		*/
		M.ordered_keys().filter(cm => { cm.value >= r });
	}


	def CPMerge(X: List[String], r: Int, V:ReverseFeatureIndex, l: Int): List[CountMap] = {
		val weights : Array[Int] = X.map(x => {
			V.getLength(x)
			}).toArray
		val ng = new Ngram(X, "");
		ng.setWeights(weights);
		val query = ng.ngrams_ordered();
		//query.foreach(println)
		val M = new ReverseKeyCountIndex();
		for( k <- 0 to (X.length - r)) {
			V.getStringsOfLength(X(k), l).foreach(M ++ _)
		}
		for( k <- (X.length - r + 1) to (X.length - 1)) {
			
		}

		List[CountMap](new CountMap("kissa"));
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