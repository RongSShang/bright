package brightnesslock.rongshangs.top.util

import android.util.Log

object BrightnessManager {
    private const val TAG = "BrightnessManager"
    private const val BRIGHTNESS_PATH = "/sys/class/backlight/panel1-backlight/brightness"
    private const val MAX_BRIGHTNESS_PATH = "/sys/class/backlight/panel1-backlight/max_brightness"

    enum class BrightnessState {
        LOCKED,
        SYSTEM,
        UNKNOWN
    }

    fun getCurrentState(): BrightnessState {
        // Check permissions of the brightness file
        // We can use ls -l and check if it starts with -r--r--r-- (444) or -rw-r--r-- (644)
        val result = ShellUtils.execRoot("ls -l $BRIGHTNESS_PATH")
        if (!result.isSuccess) {
            Log.e(TAG, "Failed to get state: ${result.error}")
            return BrightnessState.UNKNOWN
        }

        val output = result.output.trim()
        return when {
            output.startsWith("-r--r--r--") -> BrightnessState.LOCKED
            output.startsWith("-rw-r--r--") -> BrightnessState.SYSTEM
            else -> {
                Log.w(TAG, "Unexpected permissions: $output")
                BrightnessState.UNKNOWN
            }
        }
    }

    fun lockBrightness(): Boolean {
        val command = """
            MAX_VAL=$(cat $MAX_BRIGHTNESS_PATH)
            chmod 644 $BRIGHTNESS_PATH
            echo ${'$'}MAX_VAL > $BRIGHTNESS_PATH
            chmod 444 $BRIGHTNESS_PATH
        """.trimIndent()
        
        val result = ShellUtils.execRoot(command)
        if (!result.isSuccess) {
            Log.e(TAG, "Failed to lock brightness: ${result.error}")
        }
        return result.isSuccess
    }

    fun restoreSystemControl(): Boolean {
        // According to requirements: chmod 644 and echo 500
        val command = """
            chmod 644 $BRIGHTNESS_PATH
            echo 500 > $BRIGHTNESS_PATH
        """.trimIndent()
        
        val result = ShellUtils.execRoot(command)
        if (!result.isSuccess) {
            Log.e(TAG, "Failed to restore system control: ${result.error}")
        }
        return result.isSuccess
    }
}
