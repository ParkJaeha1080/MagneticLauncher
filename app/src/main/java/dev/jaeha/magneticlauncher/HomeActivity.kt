package dev.jaeha.magneticlauncher

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class HomeActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.rgb(18, 18, 24)
        window.navigationBarColor = Color.rgb(18, 18, 24)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(20, 24, 20, 16)
            setBackgroundColor(Color.rgb(18, 18, 24))
        }
        val title = TextView(this).apply {
            text = "Magnetic Launcher"
            textSize = 25f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER_VERTICAL
            setPadding(4, 8, 4, 20)
        }
        root.addView(title)

        val scroll = ScrollView(this)
        val grid = GridLayout(this).apply {
            columnCount = 4
            alignmentMode = GridLayout.ALIGN_BOUNDS
            useDefaultMargins = true
        }
        scroll.addView(grid)
        root.addView(scroll, LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f
        ))

        val pm = packageManager
        val launcherIntent = Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER)
        val apps = try {
            @Suppress("DEPRECATION")
            pm.queryIntentActivities(launcherIntent, PackageManager.MATCH_ALL)
        } catch (_: Exception) {
            emptyList()
        }.distinctBy { it.activityInfo.packageName }
            .sortedBy { it.loadLabel(pm).toString().lowercase() }

        for (app in apps) {
            val label = app.loadLabel(pm).toString()
            val icon = app.loadIcon(pm)
            val item = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER
                setPadding(4, 12, 4, 12)
                isClickable = true
                isFocusable = true
                background = android.graphics.drawable.GradientDrawable().apply {
                    cornerRadius = 22f
                    setColor(Color.rgb(35, 35, 45))
                }
                setOnClickListener {
                    val launch = pm.getLaunchIntentForPackage(app.activityInfo.packageName)
                    if (launch != null) startActivity(launch)
                }
            }
            val iconView = android.widget.ImageView(this).apply {
                setImageDrawable(icon)
                contentDescription = label
                layoutParams = LinearLayout.LayoutParams(56, 56)
            }
            val labelView = TextView(this).apply {
                text = label
                textSize = 11f
                maxLines = 2
                gravity = Gravity.CENTER
                setTextColor(Color.WHITE)
                setPadding(2, 6, 2, 0)
            }
            item.addView(iconView)
            item.addView(labelView)
            val lp = GridLayout.LayoutParams().apply {
                width = 0
                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                setMargins(3, 3, 3, 3)
            }
            grid.addView(item, lp)
        }
        setContentView(root)
    }
}
