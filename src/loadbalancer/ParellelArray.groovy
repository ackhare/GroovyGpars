package loadbalancer

/**
 * Created by chetan on 18/11/16.
 */
import static groovyx.gpars.GParsPool.runForkJoin
import groovyx.gpars.GParsPool

class Config {
    static DATA_COUNT = 2**14
    static THREADS = 4
}

items = [] as List<Integer>
items.addAll(1..Config.DATA_COUNT)
Collections.shuffle(items)

GParsPool.withPool(Config.THREADS) {
    def computedMax = items.parallel.max()
    println "expectedMax = ${Config.DATA_COUNT}"
    println "computedMax = ${computedMax}"
}
