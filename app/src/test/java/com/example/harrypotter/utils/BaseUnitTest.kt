package com.example.harrypotter.utils

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

open class BaseUnitTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @get: Rule
    val coroutineScopeRule = MainCoroutineScopeRule()
}