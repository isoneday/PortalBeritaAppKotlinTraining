package com.imastudio.portalberitaapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.imastudio.portalberitaapp.MainActivity
import com.imastudio.portalberitaapp.fragment.BeritaFragment
import com.imastudio.portalberitaapp.fragment.InformasiFragment

class TabAdapter(
    var mainActivity: MainActivity,
    var supportFragmentManager: FragmentManager,
    var tabCount: Int
) : FragmentPagerAdapter(supportFragmentManager, tabCount) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return BeritaFragment()
            1 -> return InformasiFragment()
            else -> return BeritaFragment()
        }
    }

    override fun getCount(): Int = tabCount

}
