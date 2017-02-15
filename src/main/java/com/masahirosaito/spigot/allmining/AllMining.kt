package com.masahirosaito.spigot.allmining

import com.masahirosaito.spigot.allmining.listeners.BlockBreakListener
import com.masahirosaito.spigot.allmining.nms.NMS
import com.masahirosaito.spigot.allmining.nms.NMS_V1_11_R1
import com.masahirosaito.spigot.allmining.nms.NMS_v1_10_R1
import com.masahirosaito.spigot.mscore.Messenger
import com.masahirosaito.spigot.mscore.UpdateChecker
import com.masahirosaito.spigot.mscore.utils.register
import org.bukkit.plugin.java.JavaPlugin

class AllMining : JavaPlugin() {
    lateinit var messenger: Messenger
    lateinit var nms: NMS

    override fun onEnable() {
        messenger = Messenger(this, true)
        nms = getNMS()

        checkUpdate()

        listOf(
                BlockBreakListener(this)
        ).forEach { it.register(this) }
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    private fun checkUpdate() {
        UpdateChecker("masahirosaito-repo", "Spigot-Plugin", "AllMining").sendVersionMessage(this)
    }

    private fun getNMS(): NMS = when (server.bukkitVersion) {
        "1.10.2-R0.1-SNAPSHOT" -> NMS_v1_10_R1()
        "1.11.2-R0.1-SNAPSHOT" -> NMS_V1_11_R1()
        else -> throw Exception()
    }
}
