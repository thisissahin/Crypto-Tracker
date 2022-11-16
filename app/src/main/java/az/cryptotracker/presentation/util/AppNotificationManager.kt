package az.cryptotracker.presentation.util

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationCompat
import az.cryptotracker.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppNotificationManager @Inject constructor(
    private val application: Application,
    private val notificationManager: NotificationManager
) {

    companion object {
        const val FETCH_RATES_NOTIFICATION_ID: Int = 1996
        const val FETCH_RATES_SERVICE_CHANNEL: String = "FETCH_RATES_SERVICE_CHANNEL"
        const val NOTIFY_RATE_CHANGES_CHANNEL = "NOTIFY_RATE_CHANGES_CHANNEL"
        const val NOTIFY_RATE_CHANGES_NOTIFICATION_ID = 1997

    }

    private val ioScope = CoroutineScope(Dispatchers.IO)

    private fun getChannel(
        channelId: String,
    ): NotificationChannel? =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.getNotificationChannel(channelId)
                ?: createChannel(channelId, NotificationCompat.PRIORITY_HIGH)
        } else null

    private fun createChannel(
        channelId: String,
        importance: Int
    ): NotificationChannel? {

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                getChannelName(channelId),
                importance
            )
            notificationManager.createNotificationChannel(channel)
            channel
        } else {
            null
        }
    }

    private fun getChannelName(typeKey: String): String {
        when (typeKey) {
            FETCH_RATES_SERVICE_CHANNEL -> R.string.rate_service_channel
            NOTIFY_RATE_CHANGES_CHANNEL -> R.string.notify_rate_change_channel
            else -> R.string.app_name
        }.also { return application.getString(it) }
    }


    fun showPushBuilder(
        notificationId: Int,
        title: String,
        description: String,
        typeKey: String,
    ) {
        ioScope.launch {

            val mPriority = NotificationCompat.PRIORITY_HIGH

            val channel = getChannel(typeKey)

            val build =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && channel != null) {
                    NotificationCompat.Builder(application, channel.id)

                } else NotificationCompat.Builder(application)
            build.apply {
                setContentText(description)
                setContentTitle(title)
                setSmallIcon(R.drawable.ic_launcher_foreground)
                setAutoCancel(true)
                priority = mPriority
            }
            // build.notification.flags or Notification.FLAG_AUTO_CANCEL

            notificationManager.notify(notificationId, build.build())
        }
    }


    fun getFetchServiceNotification(): Notification {
        val channel = getChannel(FETCH_RATES_SERVICE_CHANNEL)

        val builder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && channel != null) {
            NotificationCompat.Builder(application, channel.id)

        } else NotificationCompat.Builder(application)
        builder.apply {
            setContentTitle("Crypto Tracker")
            setContentText("Fetching rates")
            setSmallIcon(R.drawable.ic_launcher_foreground)
            priority = NotificationCompat.PRIORITY_HIGH
        }
        return builder.build()
    }


}
