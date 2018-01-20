fun main(args: Array<String>) {
    Thread(Runnable {
        val cache = LocationCache.cache
        var id = 0
        UploadThread().start()
        while (true) {
            var time = System.currentTimeMillis()
            cache.addElement(LocationCache.CommonLocation(id))
            print("${id}完成上传+timestamp${System.currentTimeMillis() - time}\n")
            id++
            Thread.sleep(2000)
        }
    }).start()

}