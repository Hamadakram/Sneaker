package com.irozon.sneaker

import android.app.Activity
import android.arch.lifecycle.LifecycleObserver
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Handler
import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout

import com.irozon.sneaker.interfaces.OnSneakerClickListener
import com.irozon.sneaker.interfaces.OnSneakerDismissListener

/**
 * Created by Hammad Akram on 5/24/2017.
 */

class Sneaker(private var context: Context) : View.OnClickListener, LifecycleObserver {

    private val DEFAULT_VALUE = -100000
    private var mIconDrawable: Drawable? = null
    private var mBackgroundColor = DEFAULT_VALUE
    private var mHeight = DEFAULT_VALUE
    private var mIconColorFilterColor = DEFAULT_VALUE
    private var mIconSize = 24
    private var mTitle = ""
    private var mMessage = ""
    private var mTitleColor = DEFAULT_VALUE
    private var mMessageColor = DEFAULT_VALUE
    private var mAutoHide = true
    private var mDuration = 3000
    private var mIsCircular = false
    private var mListener: OnSneakerClickListener? = null
    private var mDismissListener: OnSneakerDismissListener? = null
    private var mTypeFace: Typeface? = null
    private var mCornerRadius = DEFAULT_VALUE
    private var mMargin = DEFAULT_VALUE
    private var targetView: ViewGroup? = null
    private var isActivity: Boolean = false
    private val sneakerView by lazy { SneakerView(context) }

    companion object {

        /**
         * Create Sneaker instance
         *
         * @param activity
         * @return Sneaker instance
         */
        @JvmStatic
        fun with(activity: Activity): Sneaker {
            return Sneaker(activity).also {
                it.setTargetView(activity)
            }
        }

        /**
         * Create Sneaker instance
         *
         * @param fragment
         * @return Sneaker instance
         */
        @JvmStatic
        fun with(fragment: Fragment): Sneaker {
            return Sneaker(fragment.requireContext()).also {
                it.setTargetView(fragment)
            }
        }

        /**
         * Create Sneaker instance
         *
         * @param viewGroup
         * @return Sneaker instance
         */
        @JvmStatic
        fun with(viewGroup: ViewGroup): Sneaker {
            return Sneaker(viewGroup.context).also {
                it.setTargetView(viewGroup)
            }
        }
    }

    private fun setTargetView(targetView: Any) {
        this.targetView =
                when (targetView) {
                    is Activity -> {
                        isActivity = true
                        targetView.window?.decorView as ViewGroup
                    }
                    is Fragment -> targetView.view as ViewGroup
                    is ViewGroup -> targetView
                    else -> null
                }
    }

    /**
     * Hides the sneaker
     */
    fun hide() {
        removeView(sneakerView)
        mDismissListener?.onDismiss()
    }

    /**
     * Sets the title of the sneaker
     *
     * @param title string value of title
     * @return
     */
    fun setTitle(title: String): Sneaker {
        mTitle = title
        return this.also { mTitle = title }
    }

    /**
     * Sets the title of the sneaker with color
     *
     * @param title string value of title
     * @param color Color resource for title text
     * @return
     */
    fun setTitle(title: String, color: Int): Sneaker {
        mTitle = title
        mTitleColor = try {
            ContextCompat.getColor(context, color)
        } catch (e: Exception) {
            color
        }
        return this
    }

    /**
     * Sets the message to sneaker
     *
     * @param message String value of message
     * @return
     */
    fun setMessage(message: String): Sneaker {
        mMessage = message
        return this
    }

    /**
     * Sets the message to sneaker with color
     *
     * @param message String value of message
     * @param color   Color resource for message text
     * @return
     */
    fun setMessage(message: String, color: Int): Sneaker {
        mMessage = message
        mMessageColor = try {
            ContextCompat.getColor(context, color)
        } catch (e: Exception) {
            color
        }
        return this
    }

    /**
     * Sets the icon to sneaker
     *
     * @param icon Icon resource for sneaker
     * @return
     */
    fun setIcon(@DrawableRes icon: Int): Sneaker {
        setIcon(icon, DEFAULT_VALUE, false)
        return this
    }

    /**
     * Sets the icon to sneaker
     *
     * @param icon Icon drawable for sneaker
     * @return
     */
    fun setIcon(icon: Drawable): Sneaker {
        setIcon(icon, DEFAULT_VALUE, false)
        return this
    }

    /**
     * Sets the icon to sneaker with circular option
     *
     * @param icon
     * @param isCircular If icon is round or not
     * @return
     */
    fun setIcon(@DrawableRes icon: Int, isCircular: Boolean): Sneaker {
        setIcon(icon, DEFAULT_VALUE, isCircular)
        return this
    }

    /**
     * Sets the icon to sneaker with circular option
     *
     * @param icon
     * @param isCircular If icon is round or not
     * @return
     */
    fun setIcon(icon: Drawable, isCircular: Boolean): Sneaker {
        setIcon(icon, DEFAULT_VALUE, isCircular)
        return this
    }

    fun setIcon(@DrawableRes icon: Int, tintColor: Int): Sneaker {
        setIcon(icon, tintColor, false)
        return this
    }

    fun setIcon(icon: Drawable, tintColor: Int): Sneaker {
        setIcon(icon, tintColor, false)
        return this
    }

    /**
     * Sets the icon to sneaker with circular option and icon tint
     *
     * @param icon
     * @param tintColor  Icon tint color
     * @param isCircular If icon is round or not
     * @return
     */
    fun setIcon(@DrawableRes icon: Int, tintColor: Int, isCircular: Boolean): Sneaker {
        mIconDrawable = ContextCompat.getDrawable(context, icon)
        mIsCircular = isCircular
        mIconColorFilterColor = Utils.getColor(context, tintColor)
        return this
    }

    /**
     * Sets the icon to sneaker with circular option and icon tint
     *
     * @param icon
     * @param tintColor  Icon tint color
     * @param isCircular If icon is round or not
     * @return
     */
    fun setIcon(icon: Drawable, tintColor: Int, isCircular: Boolean): Sneaker {
        mIconDrawable = icon
        mIsCircular = isCircular
        mIconColorFilterColor = Utils.getColor(context, tintColor)
        return this
    }

    /**
     * Sets the size of the icon.
     *
     * @param size New icon size.
     */
    fun setIconSize(size: Int): Sneaker {
        mIconSize = size
        return this
    }

    /**
     * Sets the corner radius for round corner sneaker.
     *
     * @param radius Corner radius.
     */
    fun setCornerRadius(radius: Int): Sneaker {
        setCornerRadius(radius, DEFAULT_VALUE)
        return this
    }

    /**
     * Sets the corner radius for round corner sneaker with margin.
     *
     * @param radius Corner radius.
     * @param margin margin.
     */
    fun setCornerRadius(radius: Int, margin: Int): Sneaker {
        mCornerRadius = radius
        mMargin = margin
        return this
    }

    /**
     * Disable/Enable auto hiding sneaker
     *
     * @param autoHide
     * @return
     */
    fun autoHide(autoHide: Boolean): Sneaker {
        mAutoHide = autoHide
        return this
    }

    /**
     * Sets the height to sneaker
     *
     * @param height Height value for sneaker
     * @return
     */
    fun setHeight(height: Int): Sneaker {
        mHeight = height
        return this
    }

    /**
     * Sets the duration for sneaker.
     * After this duration sneaker will disappear
     *
     * @param duration
     * @return
     */
    fun setDuration(duration: Int): Sneaker {
        mDuration = duration
        return this
    }

    /**
     * Sets the click listener to sneaker
     *
     * @param listener
     * @return
     */
    fun setOnSneakerClickListener(listener: OnSneakerClickListener): Sneaker {
        mListener = listener
        return this
    }

    /**
     * Sets the dismiss listener to sneaker
     */
    fun setOnSneakerDismissListener(listener: OnSneakerDismissListener): Sneaker {
        mDismissListener = listener
        return this
    }

    /**
     * Set font for title and message
     *
     * @param typeface
     * @return
     */
    fun setTypeface(typeface: Typeface): Sneaker {
        mTypeFace = typeface
        return this
    }

    /**
     * Shows sneaker with custom color
     *
     * @param backgroundColor Color resource for sneaker background color
     */
    fun sneak(backgroundColor: Int) {
        mBackgroundColor = try {
            ContextCompat.getColor(context, backgroundColor)
        } catch (e: Exception) {
            backgroundColor
        }
        sneakView()
    }

    /**
     * Shows warning sneaker with fixed icon, background color and icon color.
     * Icons, background and text colors for this are not customizable
     */
    fun sneakWarning() {
        mBackgroundColor = Color.parseColor("#ffc100")
        mTitleColor = Color.parseColor("#000000")
        mMessageColor = Color.parseColor("#000000")
        mIconColorFilterColor = Color.parseColor("#000000")
        mIconDrawable = ContextCompat.getDrawable(context, R.drawable.ic_warning)

        sneakView()
    }

    /**
     * Shows error sneaker with fixed icon, background color and icon color.
     * Icons, background and text colors for this are not customizable
     */
    fun sneakError() {
        mBackgroundColor = Color.parseColor("#ff0000")
        mTitleColor = Color.parseColor("#FFFFFF")
        mMessageColor = Color.parseColor("#FFFFFF")
        mIconColorFilterColor = Color.parseColor("#FFFFFF")
        mIconDrawable = ContextCompat.getDrawable(context, R.drawable.ic_error)

        sneakView()
    }

    /**
     * Shows success sneaker with fixed icon, background color and icon color.
     * Icons, background and text colors for this are not customizable
     */
    fun sneakSuccess() {
        mBackgroundColor = Color.parseColor("#2bb600")
        mTitleColor = Color.parseColor("#FFFFFF")
        mMessageColor = Color.parseColor("#FFFFFF")
        mIconColorFilterColor = Color.parseColor("#FFFFFF")
        mIconDrawable = ContextCompat.getDrawable(context, R.drawable.ic_success)

        sneakView()
    }

    /**
     * Creates the view and sneaks in
     */
    private fun sneakView() {
        // Main layout
        targetView?.let {
            val layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    if (mHeight == DEFAULT_VALUE && isActivity)
                        Utils.getStatusBarHeight(it) + Utils.convertToDp(context, 56f)
                    else if (mHeight == DEFAULT_VALUE && !isActivity)
                        Utils.convertToDp(context, 56f)
                    else
                        Utils.convertToDp(context, mHeight.toFloat())
            )
            if (mMargin != DEFAULT_VALUE) {
                val margin = Utils.convertToDp(context, mMargin.toFloat())
                layoutParams.setMargins(margin, margin, margin, margin)
            }

            with(sneakerView) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    elevation = 6f

                this.layoutParams = layoutParams
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_VERTICAL
                setPadding(46, if (isActivity) Utils.getStatusBarHeight(it) else 0, 46, 0)
                setBackground(mBackgroundColor, mCornerRadius)
                setIcon(mIconDrawable, mIsCircular, Utils.convertToDp(context, mIconSize.toFloat()), mIconColorFilterColor)
                setTextContent(mTitle, mTitleColor, mMessage, mMessageColor, mTypeFace)
                setOnClickListener(this@Sneaker)
            }
            removeExistingSneakerView(it)
            it.addView(sneakerView, 0)

            sneakerView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.popup_show))
            if (mAutoHide) {
                val handler = Handler()
                handler.removeCallbacks(null)
                handler.postDelayed({
                    removeView(sneakerView)
                    mDismissListener?.onDismiss()
                }, mDuration.toLong())
            }
        }
    }

    fun sneakCustom(layout: View): Sneaker {
        sneakerView.setCustomView(layout)

        targetView?.let {
            removeExistingSneakerView(it)
            it.addView(sneakerView)

            sneakerView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.popup_show))
            if (mAutoHide) {
                val handler = Handler()
                handler.removeCallbacks(null)
                handler.postDelayed({
                    removeView(sneakerView)
                    mDismissListener?.onDismiss()
                }, mDuration.toLong())
            }
        }
        return this
    }

    /**
     * Gets the existing sneaker and removes before adding new one
     *
     * @param parent
     */
    private fun removeExistingSneakerView(parent: ViewGroup) {
        parent.findViewById<LinearLayout>(R.id.mainLayout)?.let {
            removeView(it, false)
        }
    }

    private fun removeView(view: View?, animate: Boolean = true) {
        view?.let {
            if (animate) it.startAnimation(AnimationUtils.loadAnimation(context, R.anim.popup_hide))
            targetView?.removeView(it)
        }
    }

    /**
     * Sneaker on click
     *
     * @param view
     */
    override fun onClick(view: View) {
        mListener?.onSneakerClick(view)
        removeView(sneakerView)
    }

    fun getView(): ViewGroup = sneakerView
}
