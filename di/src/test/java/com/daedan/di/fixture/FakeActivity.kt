package com.daedan.di.fixture

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.daedan.di.DiComponent
import com.daedan.di.module
import com.daedan.di.util.activityRetainedScope
import com.daedan.di.util.activityScope
import com.daedan.di.util.autoViewModels
import com.daedan.di.util.inject

class FakeActivity : ComponentActivity() {
    val viewModel by autoViewModels<TestViewModel>()

    val activityScope = activityScope()

    val activityRetainedScope = activityRetainedScope()

    val activityArgument by inject<Child2>(activityScope)

    val activityRetainedArgument by inject<Child3>(activityRetainedScope)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel
        activityArgument
        activityRetainedArgument
    }
}

class FakeInvalidScopeActivity : ComponentActivity() {
    val activityScope = activityScope()

    val activityArgument by inject<Parent>(activityScope)

    override fun onCreate(savedInstanceState: Bundle?) {
        activityArgument
        super.onCreate(savedInstanceState)
    }
}

fun DiComponent.testModule() =
    module {
        scope<TestViewModel> {
            scoped { Child1() }
        }
        scope<FakeActivity> {
            scoped { Child2() }
            scoped { Child3() }
        }
        viewModel {
            TestViewModel(get(scope = it))
        }
    }

fun DiComponent.invalidScopeModule() =
    module {
        single { Child2() }
        scope<FakeInvalidScopeActivity> {
            scoped { Child1() }
        }
        single { Parent(get(), get()) }
    }
