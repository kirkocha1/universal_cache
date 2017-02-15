package lib

import java.util.*

class Cache<L: ILink, R: IResult>(size: Int, val solution: IResolve<L, R>) : Iterable<Pair<L, R>> {

    private val cacheMap = HashMap<L, R>(size)
    private val queue = ArrayDeque<L>()

    override operator fun iterator(): Iterator<Pair<L, R>> {
        return cacheMap.toList().iterator()
    }

    fun load(key: L): R{
        var result = cacheMap[key]
        if (result == null) {
            result = solution.resolve(key)
        }
        when (cacheMap.size) {
            in 0..10 -> {
                queue.add(key)
            }
            else -> {
                println("Cache is full remove last value")
                cacheMap.remove(queue.poll())
            }
        }
        cacheMap.put(key, result)
        return result
    }

    fun printCache() {
        for (pair in cacheMap.toList()) {
            println(pair.first.toString() + " " + pair.second.toString())
        }
    }
}

interface ILink

interface IResult

interface IResolve<L: ILink, R: IResult>{
    fun resolve(link: L): R
}
