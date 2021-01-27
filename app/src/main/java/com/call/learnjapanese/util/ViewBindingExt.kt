package com.call.learnjapanese.util

import android.app.Activity
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * @author [Iga Noviyanti R] on 22/01/2021 at 8:53.
 */
class ActivityViewBindingExtension <T : ViewBinding>(
    private val bindingClass: Class<T>
) : ReadOnlyProperty<Activity, T> {

    private var binding: T? = null

    override fun getValue(thisRef: Activity, property: KProperty<*>): T {
        binding?.let { binding ->
            return binding
        }

        val bindingMethod = bindingClass.getMethod(
            inflateMethod,
            LayoutInflater::class.java
        )

        val viewBinding = bindingMethod.invoke(null, thisRef.layoutInflater) as T

        thisRef.setContentView(viewBinding.root)
        return viewBinding.also { this.binding = it }
    }
}
private const val inflateMethod = "inflate"

inline fun <reified T : ViewBinding> viewBinding() =
    ActivityViewBindingExtension(T::class.java)