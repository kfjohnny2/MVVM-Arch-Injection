package com.johnnylee.mvvm_arch_injection.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.johnnylee.mvvm_arch_injection.R
import com.johnnylee.mvvm_arch_injection.base.BaseFragment
import com.johnnylee.mvvm_arch_injection.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>() {
    override fun layoutRes(): Int = R.layout.fragment_main

    override fun viewModel(): Class<MainViewModel> = MainViewModel::class.java

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
                                Bundle? ): View? {
        binding.mainViewModel = viewModel

        return binding.root
    }
}
