package com.danielecampogiani.underlinepageindicator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup


class UnderlinePageIndicator @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : View(context, attrs, defStyle), ViewPager.OnPageChangeListener {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var viewPager: ViewPager? = null
    private var currentPage: Int = 0
    private var positionOffset: Float = 0.toFloat()

    private var location = IntArray(2)
    private val tabTexts = mutableListOf<View>()

    init {
        if (!isInEditMode) {
            val typedValue = TypedValue()
            val theme = context.theme
            theme.resolveAttribute(R.attr.colorAccent, typedValue, true)
            val defaultSelectedColor = typedValue.data
            setSelectedColor(defaultSelectedColor)
        }
    }

    private fun setSelectedColor(selectedColor: Int) {
        paint.color = selectedColor
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (viewPager == null) {
            return
        }
        val count = viewPager?.adapter?.count ?: 0
        if (count == 0) {
            return
        }

        if (currentPage >= count) {
            setCurrentItem(count - 1)
            return
        }
        val top = 0f
        val bottom = height.toFloat()

        val left = currentLeft
        val right = left + currentWidth

        canvas.drawRect(left, top, right, bottom, paint)
    }

    private val currentLeft: Float
        get() {

            val firstView = tabTexts.getOrNull(currentPage)
            val secondView = tabTexts.getOrNull(currentPage + 1)

            firstView?.getLocationOnScreen(location)
            val firstX = location[0]
            secondView?.getLocationOnScreen(location)
            val secondX = location[0]

            return firstX.toFloat() + (secondX.toFloat() - firstX.toFloat()) * positionOffset
        }

    private val currentWidth: Float
        get() {
            val firstWidth = tabTexts.getOrNull(currentPage)?.width
            val secondWidth = tabTexts.getOrNull(currentPage + 1)?.width

            return if (firstWidth == null) 0f
            else {
                if (secondWidth == null) firstWidth.toFloat()
                else {
                    ((firstWidth.toFloat() * (1 - positionOffset)) + (secondWidth.toFloat() * positionOffset))
                }
            }
        }

    private fun setViewPager(viewPager: ViewPager) {
        if (this.viewPager == viewPager) {
            return
        }

        this.viewPager?.removeOnPageChangeListener(this)

        if (viewPager.adapter == null) {
            return
        }
        this.viewPager = viewPager
        viewPager.addOnPageChangeListener(this)
        currentPage = viewPager.currentItem
    }

    fun setTabLayoutAndViewPager(tabLayout: TabLayout, viewPager: ViewPager) {
        tabLayout.setupWithViewPager(viewPager)

        val tabStrip = tabLayout.getChildAt(0)

        if (tabStrip is ViewGroup) {

            val childCount = tabStrip.childCount

            (0 until childCount)
                    .mapNotNull {
                        val tabView = tabStrip.getChildAt(it) as? ViewGroup
                                ?: return@mapNotNull null
                        val tabChildrenCount = tabView.childCount
                        tabView.getChildAt(tabChildrenCount - 1)
                    }
                    .forEach { tabTexts.add(it) }
        }
        setViewPager(viewPager)

        invalidate()
    }

    private fun setCurrentItem(item: Int) {
        if (viewPager == null) {
            return
        }
        viewPager?.currentItem = item
        currentPage = item
        invalidate()
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        currentPage = position
        this.positionOffset = positionOffset
        invalidate()
    }

    override fun onPageSelected(position: Int) {
    }
}