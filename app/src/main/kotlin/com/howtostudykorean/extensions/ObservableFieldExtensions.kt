package com.howtostudykorean.extensions

import androidx.databinding.Observable
import androidx.databinding.ObservableField

fun <T> ObservableField<T>.addOnPropertyChangedCallback(onChanged: () -> Unit) = this.addOnPropertyChangedCallback(createOnPropertyChangedCallback(onChanged))

fun createOnPropertyChangedCallback(onChanged: () -> Unit): Observable.OnPropertyChangedCallback {
    return object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            onChanged()
        }
    }
}