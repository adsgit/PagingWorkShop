package com.example.pagingworkshop.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.pagingworkshop.home.model.MyRepository
import com.example.pagingworkshop.home.model.local.LocalDatabase

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    val respository: MyRepository = MyRepository(LocalDatabase.getInstance(application).userDao())



}