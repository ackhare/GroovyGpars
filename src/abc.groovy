/**
 * Created by chetan on 17/11/16.
 */
import twitter4j.Twitter
import twitter4j.Query
import groovyx.gpars.GParsExecutorsPool
import twitter4j.TwitterAdapter
import twitter4j.TwitterFactory

def recentTweets(api, queryStr) {
    def query = new Query(queryStr)
    println query
    query.count = 5		// tweets to return
    query.lang = "en"		// language
    def tweets = api.search(query).tweets
    def threadName = Thread.currentThread().name
    println 'jjjjj'
    tweets.collect {
        "[${threadName}-${queryStr}] @${it.fromUser}: ${it.text}"
    }
}

Twitter api = TwitterFactory.getSingleton();
GParsExecutorsPool.withPool {
    def retrieveTweets = { query ->
        println 'mmlmllmlmlmlmlml'
        def tweets = recentTweets(api, query)
        tweets.each {
            println "${it}"
        }
    }

    ['#trump','#modi','#putin'].each {
        retrieveTweets.callAsync(it)
    }
}