package brightnesslock.rongshangs.top.util

import android.util.Log
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader

object ShellUtils {
    private const val TAG = "ShellUtils"

    fun isRootAvailable(): Boolean {
        return try {
            val process = Runtime.getRuntime().exec("su")
            val os = DataOutputStream(process.outputStream)
            os.writeBytes("exit\n")
            os.flush()
            val exitValue = process.waitFor()
            exitValue == 0
        } catch (e: Exception) {
            Log.e(TAG, "Root check failed", e)
            false
        }
    }

    fun execRoot(command: String): ShellResult {
        var process: Process? = null
        var os: DataOutputStream? = null
        var isReader: BufferedReader? = null
        var errReader: BufferedReader? = null
        
        try {
            process = Runtime.getRuntime().exec("su")
            os = DataOutputStream(process.outputStream)
            os.writeBytes("$command\n")
            os.writeBytes("exit\n")
            os.flush()

            val exitCode = process.waitFor()
            
            isReader = BufferedReader(InputStreamReader(process.inputStream))
            val output = isReader.readLines().joinToString("\n")
            
            errReader = BufferedReader(InputStreamReader(process.errorStream))
            val error = errReader.readLines().joinToString("\n")

            return ShellResult(exitCode, output, error)
        } catch (e: Exception) {
            Log.e(TAG, "Command execution failed: $command", e)
            return ShellResult(-1, "", e.message ?: "Unknown error")
        } finally {
            try {
                os?.close()
                isReader?.close()
                errReader?.close()
                process?.destroy()
            } catch (e: Exception) {
                // Ignore
            }
        }
    }

    data class ShellResult(
        val exitCode: Int,
        val output: String,
        val error: String
    ) {
        val isSuccess: Boolean get() = exitCode == 0
    }
}
