package com.example.testlayout

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.activity_two.*


class TwoActivity : AppCompatActivity() {

    companion object {
        const val PERSONAL_FRAGMENT = 0
        const val ORGANIZATION_FRAGMENT = 1
        const val HARD_DISK_MANAGEMENT_FRAGMENT = 2
    }


    private var controllerPersonalFragment: NavController? = null
    private var controllerOrganizationFragment: NavController? = null
    private var controllerHardDiskManagementFragment: NavController? = null
    private var fragmentList = mutableListOf<ConstraintLayout>()
    private var showFragment = 0
    private var isBack = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)
        initNavigationController()
        initTab()
    }

    private fun initNavigationController() {
        controllerPersonalFragment =
            Navigation.findNavController(this@TwoActivity, R.id.personal_fragment)
        controllerOrganizationFragment =
            Navigation.findNavController(this@TwoActivity, R.id.organization_fragment)
        controllerHardDiskManagementFragment =
            Navigation.findNavController(this@TwoActivity, R.id.hard_disk_management_fragment)
    }

    private fun initTab() {
        fragmentList.add(personal_fragment_layout)
        fragmentList.add(organization_fragment_layout)
        fragmentList.add(hard_disk_management_fragment_layout)
        //设置导航栏菜单项Item选中监听
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.personalFragment -> {
                    showFragmentNavigation(PERSONAL_FRAGMENT)
                }
                R.id.organizationFragment -> {
                    showFragmentNavigation(ORGANIZATION_FRAGMENT)
                }
                R.id.hardDiskManagementFragment -> {
                    showFragmentNavigation(HARD_DISK_MANAGEMENT_FRAGMENT)
                }
            }
            true
        }
    }

    private fun showFragmentNavigation(index: Int) {
        showFragment = index
        showFragment(index)
    }

    private fun showFragment(index: Int) {
        fragmentList.forEachIndexed { i, layout ->
            layout.visibility = if (index == i) {
                View.VISIBLE
            } else {
                View.GONE   
            }
        }
    }

    override fun onBackPressed() {
        when (showFragment) {
            PERSONAL_FRAGMENT -> {
                isBack = controllerPersonalFragment?.popBackStack() ?: false
            }
            ORGANIZATION_FRAGMENT -> {
                isBack = controllerOrganizationFragment?.popBackStack() ?: false
            }
            HARD_DISK_MANAGEMENT_FRAGMENT -> {
                isBack = controllerHardDiskManagementFragment?.popBackStack() ?: false
            }
        }
        if (!isBack) {
            super.onBackPressed()
        }

    }


}
