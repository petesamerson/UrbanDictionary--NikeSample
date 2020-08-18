package com.example.urban.resource

import androidx.test.espresso.IdlingResource


object EspressoIdlingResource {
        private val RESOURCE: String = "GLOBAL"

        private val mCountingIdlingResource : SimpleCountingIdlingResource = SimpleCountingIdlingResource(
            RESOURCE
        )

        fun increment() {
            mCountingIdlingResource.increment()
        }

         fun decrement() {
            mCountingIdlingResource.decrement()
        }
        fun getIdlingResource(): IdlingResource? {
            return mCountingIdlingResource
        }
}