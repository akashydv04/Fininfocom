package shyam.gunsariya.fininfocom.module

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import shyam.gunsariya.fininfocom.adapter.ListAdapter
import shyam.gunsariya.fininfocom.fragment.listItem.ListItemViewModel
import shyam.gunsariya.fininfocom.fragment.ui.login.LoginViewModel

val repositoryModule = module {
}

val viewModelModule = module {
    //dashboard view model
    viewModel { LoginViewModel() }
    viewModel { ListItemViewModel() }
}

val adapterModule = module {
    //dashboard view model
    single { ListAdapter() }
}