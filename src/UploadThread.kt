import java.util.*

class UploadThread : Thread() {

    private val cache: LocationCache = LocationCache.cache
    override fun run() {
        while (true) {
            while (!cache.isEmpty()) {
                var time = System.currentTimeMillis()
                val upload = cache.pollElement()
                if (upload != null) {
                    print("正在上传+${upload.id}+timestamp:$time\n")
                    Thread.sleep(1000)
                    print("${upload.id}上传完毕\n+timestamp:${System.currentTimeMillis() - time}\n")
                    cache.removeElement()
                }
            }
            Thread.sleep(100)
            print("我在等待~\n")
        }
    }
}