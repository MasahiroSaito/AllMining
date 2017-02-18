package com.masahirosaito.spigot.allmining

import com.google.gson.Gson
import org.bukkit.plugin.java.JavaPlugin
import java.net.URL

class UpdateChecker(val plugin: JavaPlugin) {

    data class Latest(val tag_name: String = "", val html_url: String = "")

    val url = URL("https://api.github.com/repos/MasahiroSaito/AllMining/releases/latest")

    fun sendVersionMessage() {
        Thread {
            val latest = getLatest(url)
            val latestVersion = latest.tag_name
            if (plugin.description.version != latestVersion) {
                plugin.logger.info("New version $latestVersion available!")
                plugin.logger.info("Download from => ${latest.html_url}")
            }
        }.start()
    }

    private fun getLatest(url: URL): Latest =
            Gson().fromJson(url.openConnection().inputStream.bufferedReader().readLine(), Latest::class.java)
}