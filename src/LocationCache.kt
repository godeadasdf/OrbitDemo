import org.jetbrains.annotations.Mutable
import java.util.*
import java.util.concurrent.LinkedBlockingDeque
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class LocationCache {

    class CommonLocation(val id: Int)

    companion object {
        var cache: LocationCache = LocationCache()
            private set
    }

    private constructor()

    private var head = 0

    private var tail = 0

    private var indexQueue: MutableList<Int> = ArrayList()

    private var locations: MutableMap<Int, CommonLocation> = HashMap()

    fun addElement(commonLocation: CommonLocation) {
        synchronized(this) {
            tail += 1
            locations.put(commonLocation.id, commonLocation)
            indexQueue.add(commonLocation.id)
        }
    }

    fun pollElement(): CommonLocation? {
        if (tail - head > 0) {
            val index = indexQueue[head]
            return locations[index]
        }
        return null
    }

    fun removeElement(): CommonLocation? {
        synchronized(this) {
            if (tail - head > 0) {
                val temp = indexQueue[head]
                head += 1
                return locations[temp]
            }
            return null
        }
    }

    fun setData(locations: MutableMap<Int, CommonLocation>) {
        this.locations?.apply {
            clear()
        }
        this.locations = locations

    }

    fun isEmpty() = synchronized(this) { head - tail == 0 }
}